package com.example.deliveryfood.api.service

import com.hyewon.deliveryfood.vo.Delivery_Address
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap

interface AddressService {

    @GET("user/address")
    fun selectUserAddress(@Query("MEM_ID") MEM_ID : String) : Observable<List<Delivery_Address>>

    @PUT("user/address/{MEM_ID}")
    @Headers("Content-Type: application/json")
    fun changeDefaultAddress(@Path("MEM_ID") MEM_ID : String, @Body DELIVERY_ADDRESS_MAIN_ADDRESS : String) : Observable<Int>
}