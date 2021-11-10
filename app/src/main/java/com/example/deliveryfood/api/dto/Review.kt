package com.hyewon.deliveryfood.vo

data class Review(val REVIEW_NO : Int, val PRODUCT_NO : String, val MEM_ID : String, val REVIEW_TITLE : String, val REVIEW_CONTENT : String, val REVIEW_DATE : String, val REVIEW_BEST_REVIEW : Int = 0)
