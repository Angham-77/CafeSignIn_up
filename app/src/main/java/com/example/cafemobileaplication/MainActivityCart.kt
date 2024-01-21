package com.example.cafemobileaplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.cafemobileaplication.Model.Cart
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Feedback

class MainActivityCart : AppCompatActivity(), CartAdapter.OnItemDeletedListener, CartAdapter.OnPriceUpdateListener {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var dbHelper: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        dbHelper = DataBaseHelper(this)
        val cartItemsList = dbHelper.getAllCartItems().toMutableList()

        val cartListView: ListView = findViewById(R.id.cartlistView)
        cartAdapter = CartAdapter(this, R.layout.single_cart_item, cartItemsList, dbHelper)
        cartListView.adapter = cartAdapter

        // Set listeners for the adapter
        cartAdapter.onItemDeletedListener = this
        cartAdapter.onPriceUpdateListener = this
    }

    override fun onPriceUpdated() {
        recalculateTotalPrice()
    }

    private fun recalculateTotalPrice() {
        val totalPrice = dbHelper.calculateTotalPrice()
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
        totalPriceTextView.text = "Total: $${totalPrice}"
    }

    override fun onItemDeleted() {
        // Handle UI update here, such as showing a Toast or refreshing the list
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
        // Optional: Refresh the list from the database
        refreshCartList()
    }
    private fun refreshCartList() {
        val dbHelper = DataBaseHelper(this)
        val updatedCartItemsList = dbHelper.getAllCartItems().toMutableList()
        cartAdapter.clear()
        cartAdapter.addAll(updatedCartItemsList)
        cartAdapter.notifyDataSetChanged()

        recalculateTotalPrice()
    }
   /* fun onPlaceOrderClicked(view: View) {
        val customerId = getCurrentUserId() // Implement method to get current user ID
        if (dbHelper.placeOrder(customerId)) {
            Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()
            // Handle post-order placement logic, like going back to the main screen
        } else {
            Toast.makeText(this, "Failed to place order.", Toast.LENGTH_SHORT).show()
        }
    }*/
    fun addToCartBtn(view: View) {

        val FeedbackText = findViewById<EditText>(R.id.editTextFeedback).text.toString()
        val ratingBar = findViewById<RatingBar>(R.id.ratingBarFeedback)
        val Rating = ratingBar.rating.toDouble()
        val message = findViewById<TextView>(R.id.textViewMessageFeedback)



        if(FeedbackText.isEmpty() ) // First and last name are required
        // Toast.makeText(this,"First name and last name are required!",Toast.LENGTH_LONG).show()
            message.text = "Feedback is required!"
        else if(FeedbackText.isEmpty()) // // User name and password are required
        //  Toast.makeText(this,"User name and Password are required!",Toast.LENGTH_LONG).show()
            message.text = "Feedback Content is required!"
        else { // Save data

            val newFeedback = Feedback(-1, 0, FeedbackText, Rating)
            //save data
            val mydatabase = DataBaseHelper(this)
            val result = mydatabase.addFeedback(newFeedback)

            when(result) {

                -1 -> message.text = "Error on creating new habit"
                -2 -> message.text = "Error can not open/create database"
                -3 -> message.text = "User name is already exist"
                else ->  {
                    message.text = "Thank you! Your Feedback has been add to the database successfully "
                    findViewById<Button>(R.id.buttonSubmitFeedback).isEnabled = false
                }
            }
        }
    }


}
