package com.example.deliveryfood.di

import com.example.deliveryfood.constant.BASE_URL
import com.example.deliveryfood.viewmodel.LoginViewModel
import com.example.deliveryfood.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module{

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()
    }

    single {

    }

    single{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

}

var viewModelPart = module {

    viewModel { LoginViewModel() }

    viewModel { MainViewModel() }
}

var myDiModule = listOf(viewModelPart, retrofitPart)