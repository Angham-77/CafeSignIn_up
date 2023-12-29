package com.example.cafemobileaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Customer

class MainActivityMenu : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
    }
    fun coffeeButton(view: View) {

        val intent = Intent(this, MainActivityCoffeeMenu::class.java)
        startActivity(intent)
    }
    fun teaButton(view: View) {

        val intent = Intent(this, MainActivityMenu::class.java)
        startActivity(intent)
    }
    fun cakeButton(view: View) {

        val intent = Intent(this, MainActivityCakeMenu::class.java)
        startActivity(intent)
    }
    fun backButtonTae(view: View) {
        // Handle the "Back" button press
        finish()
    }

    fun nextButtonTae(view: View) {
        // Handle the "Next" button press
        val intent = Intent(this, MainActivityCoffeeMenu::class.java)
        startActivity(intent)
    }
}