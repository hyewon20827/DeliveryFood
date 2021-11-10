package com.example.deliveryfood.api.dto

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Member(@SerializedName("mem_ID") @PrimaryKey val mem_ID : String, @SerializedName("grade_NO") var grade_NO : Int, @SerializedName("mem_PW") var mem_PW : String, @SerializedName("mem_EMAIL")val mem_EMAIL : String
                  , @SerializedName("mem_PHONE") val mem_PHONE : String, @SerializedName("mem_BIRTH") val mem_BIRTH : String, @SerializedName("mem_GENDER") @Nullable val mem_GENDER : String, @SerializedName("mem_RECOMMAND_PATH") @Nullable val mem_RECOMMAND_PATH : String
                  , @SerializedName("mem_POINT") val mem_POINT : Int)
