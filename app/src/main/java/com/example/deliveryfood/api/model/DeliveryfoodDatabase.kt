package com.example.deliveryfood.api.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deliveryfood.api.dao.SampleDao
import com.example.deliveryfood.api.dto.Member
import com.hyewon.deliveryfood.vo.Delivery_Address

@Database(entities = [Member::class, Delivery_Address::class], version = 1)
//@TypeConverters(DeliveryfoodDatabase::class)
abstract class DeliveryfoodDatabase : RoomDatabase() {

    abstract fun sampleDao() : SampleDao
}