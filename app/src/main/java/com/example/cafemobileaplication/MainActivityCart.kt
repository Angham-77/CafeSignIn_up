package com.example.cafemobileaplication

import CartAdapter
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafemobileaplication.Model.Product

class MainActivityCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)


        val cartItems = mutableListOf<Product>()
        val cartAdapter = CartAdapter(this, R.layout.single_cart_item, cartItems)
        val listView: ListView = findViewById(R.id.cartlistView)
        listView.adapter = cartAdapter

    }
}
