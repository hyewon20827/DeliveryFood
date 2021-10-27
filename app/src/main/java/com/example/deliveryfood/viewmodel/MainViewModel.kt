package com.example.deliveryfood.viewmodel

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseViewModel
import com.example.deliveryfood.model._enum.ActivityType
import com.example.deliveryfood.model._enum.PageType
import com.example.deliveryfood.model.service.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import java.lang.IllegalArgumentException

class MainViewModel() : BaseViewModel() {

    private val TAG = "MainViewModel"

    //app bar icon 선택시 activity 변경을 위한 변수
    private val _currentActivity = MutableLiveData<ActivityType>()
    val currentActivity : LiveData<ActivityType> = _currentActivity

    //fragment 화면 변경을 위한 변수
    private val _currentPageType = MutableLiveData(PageType.PAGE1)
    val currentPageType : LiveData<PageType> = _currentPageType

    //fragment 변경시 변경된 fragment에 맞는 bottom navigation item으로 변경해주기 위한 변수
    private val _currentMenuItemId = MutableLiveData(R.id.nav_home)
    val currentMenuItemId : LiveData<Int> = _currentMenuItemId

    //원격 서버에 있는 데이터베이스에 접근하기 위한 Retrofit2 객체 의존성 주입 받는 변수
    private val retrofit : Retrofit by inject(Retrofit::class.java)
    private val _service : RetrofitService
        get() = retrofit.create(RetrofitService::class.java)

    fun selectUser(MEM_ID : String, MEM_PW : String) = _service.selectUser(MEM_ID, MEM_PW).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
        Log.d(TAG, "success ${it.toString()}")
    },{
        Log.d(TAG, it.toString())
    })

    fun changeActivity(MenuItem : Int){
        _currentActivity.value = when(MenuItem){
            R.id.delivery_address_location -> ActivityType.ACTIVITY1
            R.id.cart -> ActivityType.ACTIVITY2
            else -> throw IllegalArgumentException("not found Activity layout id")
        }
    }

    fun setCurrentMenuItemId(fragment : String) {
        _currentMenuItemId.value = when(fragment){
            "HomeFragment" -> R.id.nav_home
            "RecommandFragment" -> R.id.nav_recommand
            "CategoryFragment" -> R.id.nav_category
            "SearchFragment" -> R.id.nav_search
            "MyPageFragment" -> R.id.nav_myPage
            else -> throw IllegalArgumentException("not found fragment name")
        }
    }

    fun setCurrentPage(menuItemId : Int) : Boolean{
        val pageType = getPageType(menuItemId)
        changeCurrentPage(pageType)

        return true
    }

    private fun getPageType(menuItemId : Int) : PageType{
        return when(menuItemId){
            R.id.nav_home -> PageType.PAGE1
            R.id.nav_recommand -> PageType.PAGE2
            R.id.nav_category -> PageType.PAGE3
            R.id.nav_search -> PageType.PAGE4
            R.id.nav_myPage -> PageType.PAGE5
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }

    private fun changeCurrentPage(pageType : PageType){
        if(currentPageType.value == pageType)
            return
        _currentPageType.value = pageType
    }
}