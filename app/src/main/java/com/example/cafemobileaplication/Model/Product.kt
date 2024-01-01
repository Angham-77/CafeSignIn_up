package com.example.cafemobileaplication.Model

data class Product (val productId: Int, var productName: String, var productImage:  ByteArray?, var productPrice: Double,
                    val productAvailable: Int) {
    override fun toString(): String {
        return "Product ID: $productId, Name: $productName, Price: $productPrice, Available: $productAvailable"
    }

}