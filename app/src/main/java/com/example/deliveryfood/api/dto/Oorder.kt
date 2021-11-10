package com.hyewon.deliveryfood.vo

data class Oorder(val ORDER_NO : Int, val MEM_ID : String, val ORDER_DATE : String,
                  val ORDER_NAME : String, val ORDER_SENDING_PERSON : String,
                  val ORDER_PAYMENT_FEE : Int, val ORDER_DISCOUNT_FEE : Int,
                  val ORDER_PRODUCT_FEE : Int, val ORDER_DELIVERY_FEE : Int, val ORDER_POINT : Int)
