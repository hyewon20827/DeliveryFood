package com.example.deliveryfood.api.repository

import android.content.Context
import androidx.room.Room
import com.example.deliveryfood.api.model.DeliveryfoodDatabase

private const val DATABASE_NAME = "deliveryfood-database"

class DeliveryfoodRepository private constructor(context : Context){

    private val database : DeliveryfoodDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            DeliveryfoodDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    companion object{
        private var INSTANCE : DeliveryfoodRepository? = null

        fun initialize(context : Context){
            if(INSTANCE == null){
                INSTANCE = DeliveryfoodRepository(context)
            }
        }

        fun get() : DeliveryfoodRepository {
            return INSTANCE ?:
            throw IllegalStateException("DeliveryfoodRepository must be initialized")
        }
    }
}