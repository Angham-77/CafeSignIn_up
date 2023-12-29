package com.example.cafemobileaplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivityCoffeeMenu  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coffee_menu)
    }
    fun backButtonCoffee(view: View) {
        // Handle the "Back" button press
        finish()
    }

    fun nextButtonCoffee(view: View) {
        // Handle the "Next" button press
        val intent = Intent(this, MainActivityCakeMenu::class.java)
        startActivity(intent)
    }


}