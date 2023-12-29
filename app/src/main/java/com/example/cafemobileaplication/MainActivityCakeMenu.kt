package com.example.cafemobileaplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivityCakeMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cake_menu)
    }
    fun backButtonCake(view: View) {
        // Handle the "Back" button press
        finish()
    }

    fun nextButtonCake(view: View) {
        // Handle the "Next" button press
        val intent = Intent(this, MainActivityCoffeeMenu::class.java)
        startActivity(intent)
    }
}