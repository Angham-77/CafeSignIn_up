package com.example.cafemobileaplication.Model

data class Cart  (val cartId: Int, var cartProdId: Int ,var cartProductName: String, var cartProductImage:  ByteArray?, var cartProductPrice: Double,
val cartProductQuantity: Int) {
    override fun toString(): String {
        return "Cart ID: $cartId, Name: $cartProductName, Price: $cartProductPrice, Available: $cartProductQuantity"
    }
}