package com.example.deliveryfood.view.activity

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseActivity
import com.example.deliveryfood.databinding.ActivityMainBinding
import com.example.deliveryfood.utils._enum.ActivityType
import com.example.deliveryfood.utils._enum.PageType
import com.example.deliveryfood.view.fragment.*
import com.example.deliveryfood.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //위험 권한 부여를 위해 Manifest에 선언된 uses-permissions들을 배열로 저장
    companion object{
        private val REQUIRED_PERMISSION_LIST by lazy {
            arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        }

        private val REQUEST_CODE = 1
    }

    private val mMissPermission = mutableListOf<String>()

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewmodel by viewModel<MainViewModel>()

    //자동 로그인을 위한 id, pw 변수 선언
    private var autoLoginId : String? = null
    private var autoLoginPw : String? = null

    //자동 로그인 id, pw 정보를 가져오기 위한 sharedPreference 변수 선언
    private lateinit var sharedPreferences : SharedPreferences

    override fun initStartView() {
        sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE)
        autoLoginId = sharedPreferences.getString("autoLoginId", "")
        if(autoLoginId?.isNotEmpty()!!){
            autoLoginPw = sharedPreferences.getString("autoLoginPw", "")
        }
        //sharedPreference에 자동 로그인 id, pw 정보가 있다면 login하고 session 등록할 것
//        if(autoLoginId?.isNotEmpty()!! && autoLoginPw?.isNotEmpty()!!)
//            viewmodel.selectUser(autoLoginId!!, autoLoginPw!!)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        //HomeFragment을 EntryPoint으로 지정
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commitAllowingStateLoss()

        //bottomNavigationView에 대한 Listener 지정
        viewDataBinding.navView.run{
            setOnItemSelectedListener { item ->
                viewmodel.setCurrentPage(item.itemId)
                true
            }
        }

        /*
        *    현재 mainactivity에 replace된 currentFragment LiveData를 감지하는 구간
        */
        viewmodel.currentPageType.observe(this){
            changeFragment(it)
        }

        /*
        *   BottomNavigation의 선택된 itemId를 변경하기 위해 LiveData를 감지하는 구간
        */
        viewmodel.currentMenuItemId.observe(this){
            viewDataBinding.navView.selectedItemId = it
        }

        viewmodel.also {    it ->
            it.currentPageType.observe(this){   pageType ->
                changeFragment(pageType)
            }

            it.currentMenuItemId.observe(this){ itemId ->
                viewDataBinding.navView.selectedItemId = itemId

            }

            it.currentActivity.observe(this){   activityType ->
                changeActivity(activityType)
            }
        }

        if(isVersionM()){
            checkAndRequestPermission()
        }
    }

    private fun changeActivity(activityType : ActivityType){
        val activity = when(activityType.tag){
            "delivery_address_activity" -> DeliveryAddressActivity()
            "cart_activity" -> CartActivity.newInstance(activityType.title)
            else -> IllegalArgumentException("not found enum tag")
        }
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    /*
    *   Bottom Navigation 선택시 fragment 교체
    */
    private fun changeFragment(pageType : PageType){
        val transaction = supportFragmentManager.beginTransaction()

        var targetFragment = supportFragmentManager.findFragmentByTag(pageType.tag)

        if(targetFragment == null){
            targetFragment = getFragment(pageType)
            transaction.addToBackStack(null).replace(R.id.fragment_container, targetFragment, pageType.tag)
        }

        transaction.show(targetFragment)

        PageType.values()
            .filterNot{it == pageType}
            .forEach{   type ->
                supportFragmentManager.findFragmentByTag(type.tag)?.let {
                    transaction.hide(it)
                }
            }

        transaction.commitAllowingStateLoss()
    }

    private fun getFragment(pageType : PageType) : Fragment{
        return when(pageType.tag){
            "nav_home" -> HomeFragment.newInstance(pageType.title)
            "nav_recommand" -> RecommandFragment.newInstance(pageType.title)
            "nav_category" -> CategoryFragment.newInstance(pageType.title)
            "nav_search" -> SearchFragment.newInstance(pageType.title)
            "nav_myPage" -> MyPageFragment.newInstance(pageType.title)
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }

    override fun ActivityMainBinding.onCreate() {
    }

    /*
    *   Android SDK 버전 확인하는 함수
    */
    private fun isVersionM() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    /*
    *   Android SDK 버전별 위험권환 분기 처리하는 함수
    */
    private fun checkAndRequestPermission(){
        mMissPermission.clear()
        for(permission in REQUIRED_PERMISSION_LIST){
            val result = ContextCompat.checkSelfPermission(this, permission)
            if(result === PackageManager.PERMISSION_DENIED){
                mMissPermission.add(permission)
            }
        }
        permissionListCheck()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode === REQUEST_CODE){
            for((index, value) in permissions.withIndex())
                if(grantResults[index] === PackageManager.PERMISSION_GRANTED)
                    mMissPermission.remove(value)
        }
        permissionListCheck()
    }

    private fun permissionListCheck(){
        if(mMissPermission.isNotEmpty()){
            ActivityCompat.requestPermissions(this, mMissPermission.toTypedArray(), REQUEST_CODE)
        }
    }

    /*
    *   상단 앱바 뷰 inflate해주고 아이콘 선택에 따른 분기 처리해주는 함수
    */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true;
    }

    /*
    *   상단 app bar 클릭시 호출되는 callback 함수
    */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewmodel.changeActivity(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    /*
    *   뒤로가기 버튼을 눌렀을 때 자동으로 호출되는 함수로 해당 함수에서는 현재 fragment를 가져와서 viewmodel의 변수를 변경해주는 기능을 함
    */
    override fun onBackPressed() {
        super.onBackPressed()
        val fragments = supportFragmentManager.fragments
        if(fragments.size > 1){
            val currentFragment = fragments[fragments.size - 1]
            val index = currentFragment.toString().indexOf('t', 0, true)
            val _currentFragment = currentFragment.toString().substring(0, index + 1)
            println("onBackPressed ${_currentFragment}")
            viewmodel.setCurrentMenuItemId(_currentFragment)
        }
    }
}