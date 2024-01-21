package com.example.cafemobileaplication.Model

data class Cart(val cartId: Int, var cartCusId: Int, var cartProdId: Int, var cartProductName: String, var cartProductImage:  ByteArray?, var cartProductPrice: Double,
                  var cartProductQuantity: Int) {
   // override fun toString(): String {
     //   return "Cart ID: $cartId, Name: $cartProductName, Price: $cartProductPrice, Available: $cartProductQuantity"
   // }
}