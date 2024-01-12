package com.example.cafemobileaplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Product

class MainActivityProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_listview)

        val dbHelper = DataBaseHelper(this)
        val productList = dbHelper.getAllProducts()


        val listView: ListView = findViewById(R.id.listView)

        // Inside your MainActivity
      /*  val adapter = ArrayAdapter<Product>(
            this,
            R.layout.single_item,
            R.id.productNameTextView,
            productList
        )*/
        val adapter = ProductAdapter(this, R.layout.single_item, productList)
        listView.adapter = adapter //modify this

        listView.adapter = adapter





    }
         val cartItems = mutableListOf<Product>() // Assuming Product is the data model for your items

        fun addToCart(product: Product) {
            cartItems.add(product)
            updateCartView()
        }

         fun updateCartView() {
            // Update your cart view (e.g., refresh the ListView or RecyclerView)
        }

    fun addToOrderClick(view: View) {
        Log.d("ButtonClicked", "Button clicked!")
       // val product = view.tag as Product
       // addToCart(product)
    }
        fun GoToCart(view: View){
            val intent = Intent(this, MainActivityCart::class.java)
            startActivity(intent)
            Log.d("CartUpdate", "Cart updated!")

        }

}


