package com.example.cafemobileaplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivityGoToMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.go_to_menu)
    }
    fun goToMenu(view: View) {
        // Handle the "Next" button press
        val intent = Intent(this, MainActivityMenu::class.java)
        startActivity(intent)
    }
}