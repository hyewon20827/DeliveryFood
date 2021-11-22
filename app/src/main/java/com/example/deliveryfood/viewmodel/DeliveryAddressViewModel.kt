package com.example.deliveryfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryfood.api.service.AddressService
import com.example.deliveryfood.base.BaseViewModel
import com.example.deliveryfood.utils._enum.WebviewPageType
import com.hyewon.deliveryfood.vo.Delivery_Address
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit

class DeliveryAddressViewModel() : BaseViewModel() {

    private val TAG = "DeliveryAddressActivity"

    private val _address_list = MutableLiveData<List<Delivery_Address>>()
    val address_list : LiveData<List<Delivery_Address>> = _address_list

    private val _address_list_change_status = MutableLiveData<Boolean>(false)
    val address_list_change_status : LiveData<Boolean> = _address_list_change_status

    private val _current_fragment_type = MutableLiveData<WebviewPageType>()
    val current_fragment_type : LiveData<WebviewPageType> = _current_fragment_type

    private val _address_api_result_hashmap = MutableLiveData<HashMap<String, String>>()
    val address_api_result_hashmap : LiveData<HashMap<String, String>> = _address_api_result_hashmap

    //로컬 DB 접근을 위한 Dao

    //원격 DB 접근을 위한 retrofit2
    private val retrofit : Retrofit by inject(Retrofit::class.java)
    private val _service : AddressService
        get() = retrofit.create(AddressService::class.java)

    fun selectUserAddress(MEM_ID : String) = _service.selectUserAddress(MEM_ID)

    fun changeDeliveryAddress(MEM_ID : String, DELIVERY_ADDRESS_MAIN_ADDRESS : String) = _service.changeDefaultAddress(MEM_ID, DELIVERY_ADDRESS_MAIN_ADDRESS)

    fun changeAddressListStatus(status : Boolean) {
        _address_list_change_status.value = status
    }

    fun changeWebviewFragment(webviewPageType : WebviewPageType){
        if(current_fragment_type.value == webviewPageType)
            return
        _current_fragment_type.value = webviewPageType
    }

    fun putApiResult(hashMap : HashMap<String, String>){
        if(address_api_result_hashmap.value.isNullOrEmpty()){
            _address_api_result_hashmap.value = hashMap
        }else{
            _address_api_result_hashmap.value?.clear()
            _address_api_result_hashmap.value = hashMap
        }
    }
}