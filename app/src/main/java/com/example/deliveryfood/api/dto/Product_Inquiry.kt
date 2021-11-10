package com.hyewon.deliveryfood.vo

data class Product_Inquiry(val PRODUCT_INQUIRY_NO : Int, val MEM_ID : String,
                           val PRODUCT_NO : String, val PRODUCT_INQUIRY_TITLE : String,
                           val PRODUCT_INQUIRY_CONTENT : String, val PRODUCT_INQUIRY_DATE : String,
                           val PRODUCT_INQUIRY_SECRET_STATUS : Int, val PRODUCT_INQUIRY_ANSWER : String)
