package com.example.cafemobileaplication.Model

data class Order (val orderId: Int, var CusId: Int, var orderDate: String, var orderTime: String,
                  val orderStatus: Int) {

}