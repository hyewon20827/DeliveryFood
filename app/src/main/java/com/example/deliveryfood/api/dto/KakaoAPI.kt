package com.example.deliveryfood.api.dto

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class KakaoAPI(@SerializedName("zonecode") val zonecode : String = "", @SerializedName("address") val address : String = "", @SerializedName("addressEnglish") val addressEnglish : String = "", @SerializedName("addressType") val addressType : String = "",
                    @SerializedName("userSelectedType") val userSelectedType : String = "", @SerializedName("noSelected") val noSelected : String = "", @SerializedName("userLanguageType") val userLanguageType : String = "",
                    @SerializedName("roadAddress") val roadAddress : String = "", val roadAddressEnglish : String = "", val jibunAddress : String = "",
                    val autoRoadAddress : String = "", val autoRoadAddressEnglish : String = "", val autoJibunAddress : String = "",
                    val autoJibunAddressEnglish : String = "", val buildingCode : String = "", val buildingName : String = "",
                    val apartment : String = "", val sido : String = "", val sidoEnglish : String = "", val sigungu : String = "",
                    val sigunguEnglish : String = "", val sigunguCode : String = "", val roadnameCode : String = "", val bcode : String = "",
                    val roadname : String = "", val roadnameEnglish : String = "", val bname : String = "",
                    val bnameEnglish : String = "", @Nullable val bname1 : String = "", @Nullable val bname1English : String = "",
                    val bname2 : String = "", val bname2English : String = "", val query : String = "")
