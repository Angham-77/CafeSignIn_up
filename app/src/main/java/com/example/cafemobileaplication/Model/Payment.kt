package com.example.cafemobileaplication.Model

data class Payment (val paymentId: Int, var orderID: Int, var paymentType: Int, var payAmount: Double,
                    val paymentDate: String) {
}