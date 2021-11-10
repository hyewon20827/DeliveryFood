package com.hyewon.deliveryfood.vo

data class Product(val PRODUCT_NO : String, val CATEGORY_NO : Int,
                   val DELIVERY_TYPE_NO : Int, val PRODUCT_PACKAGE_TYPE_NO : Int,
                   val PRODUCT_PRICE : Int, val PRODUCT_EXPLANATION : String,
                   val PRODUCT_SALE_UNIT : String, val PRODUCT_VOLUME : String,
                   val PRODUCT_ALLERGY_INFO : String, val PRODUCT_EXPIRATION_DATE : String,
                   val PRODUCT_ACCUMULATED_SALE : Int)
