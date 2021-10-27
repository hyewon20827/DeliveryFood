package com.example.deliveryfood.model.service

import com.example.deliveryfood.model.dto.Member
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitService {

    @POST("/users/{user}/repos")
    @FormUrlEncoded
    fun selectUser(@Path("user") MEM_ID : String, @Field("MEM_PW") MEM_PW : String) : Single<Member>
}