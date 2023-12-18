package com.example.cafemobileaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.User
//how to link the two pages together
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun loginButton(view: View) {

        val message = findViewById<TextView>(R.id.textViewMessage)
        val userName = findViewById<EditText>(R.id.editTextUserName).text.toString()
        val userPassword = findViewById<EditText>(R.id.editTextPassword).text.toString()

        if(userName.isEmpty() || userPassword.isEmpty())
            Toast.makeText(this,"Please insert Username and Password",Toast.LENGTH_LONG).show()
        else {
            val myDataBase = DataBaseHelper(this)
            val result = myDataBase.getUser(User(-1," ", " ",
                0, 0, " ", userName,userPassword))
            println("DataBase =  $result")
            if( result == -1)
                message.text = "User Not Found, Please try again"
            else if( result == -2)
                message.text = "Error Cannot Open/Create DataBase"
            else message.text = "You logged in successfully"
        }
    }

    fun registerButton(view: View) {

        val intent = Intent(this, MainActivityNewUser::class.java)
        startActivity(intent)
    }
}