package com.hyewon.deliveryfood.vo

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Delivery_Address(@SerializedName("mem_ID") @PrimaryKey val MEM_ID : String, @SerializedName("delivery_ADDRESS_MAIN_ADDRESS") val DELIVERY_ADDRESS_MAIN_ADDRESS : String,
                            @SerializedName("delivery_ADDRESS_SUB_ADDRESS") val DELIVERY_ADDRESS_SUB_ADDRESS : String, @SerializedName("delivery_ADDRESS_SENDING_MESSAGE") @Nullable val DELIVERY_ADDRESS_SENDING_MESSAGE_TIME : Int,
                            @SerializedName("delivery_ADDRESS_NOT_RELEASING_ACTION") @Nullable val DELIVERY_ADDRESS_NOT_RELEASING_ACTION : Int, @SerializedName("sending_PERSON_NAME") @Nullable val SENDING_PERSON_NAME : String,
                            @SerializedName("sending_PHONE_NUMBER") @Nullable val SENDING_PHONE_NUMBER : String, @SerializedName("default_ADDRESS") val DEFAULT_ADDRESS : Boolean = false,
                            @SerializedName("selected_ADDRESS") val SELECTED_ADDRESS : Boolean = false, @SerializedName("delivery_TYPE") val DELIVERY_TYPE : String)
