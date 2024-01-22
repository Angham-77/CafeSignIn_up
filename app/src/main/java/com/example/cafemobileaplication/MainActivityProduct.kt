package com.example.cafemobileaplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Product
import com.example.cafemobileaplication.Model.Cart
import com.example.cafemobileaplication.Model.Customer


class MainActivityProduct : AppCompatActivity() {

    private var addItemCount = 0

    private lateinit var adapter: ProductAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_listview)

        val dbHelper = DataBaseHelper(this)
        val productList = dbHelper.getAllProducts()

        val listView: ListView = findViewById(R.id.listView)

        // Pass the dbHelper instance to the adapter
        adapter = ProductAdapter(this, R.layout.single_item, productList, dbHelper)
        listView.adapter = adapter




        adapter.setOnAddToCartListener { addedProduct ->
           // val currentCustomerId = getCurrentCustomerId()

            // Create a Cart object with the current customer's ID
            val cartItem = Cart(
                cartId = -1, // Auto-incremented ID
                cartCusId = 0, // Current customer's ID
                cartProdId = addedProduct.productId, // Product ID
                cartProductName = addedProduct.productName,
                cartProductImage = addedProduct.productImage,
                cartProductPrice = addedProduct.productPrice,
                cartProductQuantity = 1
            )
            // Call the addToCart method in DataBaseHelper
            dbHelper.addItemToCart(cartItem)
        }

    }
    fun gotocart(view: View) {

        val intent = Intent(this, MainActivityCart::class.java)
        startActivity(intent)
        println("Go to Cart")
    }
    //new
    fun addToCart(view: View) {
        Log.d("MainActivityProduct", "addToCart function called")
        // Increment and log the count of how many times the button has been clicked
        addItemCount++
        Log.d("MainActivityProduct", "Add button clicked $addItemCount times")

        val dbHelper = DataBaseHelper(this)

        // Get the selected product from the adapter
        val selectedProduct = view.tag as Product
        Log.d("MainActivityProduct", "Product added to cart: ${selectedProduct.productName}")

        // Create a Cart object from the selected product
        val cartItem = Cart(
            cartId = -1,
            0,
            0,
            cartProductName = selectedProduct.productName,
            cartProductImage = selectedProduct.productImage,
            cartProductPrice = selectedProduct.productPrice,
            cartProductQuantity = 1
        )

        // Call the addItemToCart method in your database helper to add the product to the cart
        dbHelper.addItemToCart(cartItem)
        Log.d("MainActivityProduct", "Product added to cart: ${selectedProduct.productName}")
    }
    fun test(view: View){
        println("TEST test")
    }
}


