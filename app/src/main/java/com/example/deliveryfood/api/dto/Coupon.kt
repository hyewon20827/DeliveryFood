package com.hyewon.deliveryfood.vo

data class Coupon(val COUPON_NO : String, val COUPON_NAME : String,
                  val COUPON_RESTRICTION : String, val COUPON_DISCOUNT_PRICE : Int?,
                  val COUPON_DISCOUNT_RATIO : Float?, val COUPON_EXPIRATION_DATE : String)
