package com.example.cafemobileaplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Product

class MainActivityProduct : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_listview)

        val dbHelper = DataBaseHelper(this)
        val productList = dbHelper.getAllProducts()


        val listView: ListView = findViewById(R.id.listView)

        val adapter = ProductAdapter(this, R.layout.single_item, productList)
        listView.adapter = adapter //modify this

        listView.adapter = adapter

    //..........................
        // Get a reference to the Spinner in your activity
     /*   val quantitySpinner: Spinner = findViewById(R.id.quantitySpinner)

        // Define a list of quantity options (e.g., 1 to 10)
        val quantityOptions = arrayListOf<String>()
        for (i in 1..10) {
            quantityOptions.add(i.toString())
        }

        // Create an ArrayAdapter for the Spinner
        val qtyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, quantityOptions)

        // Specify the layout to use when the list of choices appears
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the Spinner
        quantitySpinner.adapter = qtyAdapter*/
        //..............------------------------------------------------
        // Assuming you have a list of cart items
       /* val cartItems: List<CartItem> = // populate this list with your cart items

        val cartAdapter = CartAdapter(this, R.layout.single_cart_item, cartItems)
        val cartListView: ListView = findViewById(R.id.cartListView)
        cartListView.adapter = cartAdapter*/

    }
    val cartItems = mutableListOf<Product>() // Assuming Product is the data model for your items

    fun addToCart(product: Product) {
        cartItems.add(product)
        updateCartView()
    }
    fun GoToCart(view: View) {

        val intent = Intent(this, MainActivityCart::class.java)
        startActivity(intent)
        println("Go to Cart")
    }
    fun gotocart(view: View) {

        val intent = Intent(this, MainActivityCart::class.java)
        startActivity(intent)
        println("Go to Cart")
    }

    fun updateCartView() {
        // Assuming you have a ListView or RecyclerView for displaying the cart items
        val cartListView: ListView = findViewById(R.id.cartlistView)

        // Create an adapter for the cart items
        val cartAdapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, cartItems)

        // Set the adapter to the cart ListView
        cartListView.adapter = cartAdapter
    }

    /*fun addToOrderClick(product: Product) {
        println("Add button")
        Log.d("ButtonClicked", "Button clicked!")

         val product2 = product.productName as Product
        addToCart(product2)
    }*/
    fun addToOrderClick(product: Product) {
        println("Add button clicked!")
        addToCart(product)
    }


}


