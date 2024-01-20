package com.example.cafemobileaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Customer
import com.example.cafemobileaplication.Model.PasswordHasher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DataBaseHelper(this)
        dbHelper.clearCart()
    }
    //chatgpt
    fun loginButton(view: View) {
        val message = findViewById<TextView>(R.id.textViewMessage)
        val userName = findViewById<EditText>(R.id.editTextUserName).text.toString()
        val userPassword = findViewById<EditText>(R.id.editTextPassword).text.toString()

        if (userName.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(this, "Please insert Username and Password", Toast.LENGTH_LONG).show()
        } else {
            val myDataBase = DataBaseHelper(this)

            // Hash the entered password
            val hashedEnteredPassword = PasswordHasher.hashPassword(userPassword)
            println("Hashed password: $hashedEnteredPassword")

            // Verify the password
            val result = myDataBase.getCustomer(Customer(-1, "", "", "", userName, hashedEnteredPassword, 0))
            println("DataBase =  $result")

            when (result) {
                -1 -> message.text = "User Not Found, Please try again"
                -2 -> message.text = "Error Cannot Open/Create DataBase"
                in 1..Int.MAX_VALUE -> {
                    message.text = "You logged in successfully"

                    // Launch the new activity
                    val intent = Intent(this, MainActivityGoToMenu::class.java)
                    startActivity(intent)
                }
                else -> message.text = "Incorrect username or password"
            }
        }
    }

    fun registerButton(view: View) {

        val intent = Intent(this, MainActivityNewUser::class.java)
        startActivity(intent)
    }

}