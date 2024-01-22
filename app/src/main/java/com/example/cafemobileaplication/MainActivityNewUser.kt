package com.example.cafemobileaplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.cafemobileaplication.Model.Customer
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.PasswordHasher
import java.util.regex.Pattern


class MainActivityNewUser  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user)
    }

    fun saveNewUserButton(view: View) {
        val CusfullName = findViewById<EditText>(R.id.editTextFullName).text.toString()
        val CusEmail = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        val CusUserName = findViewById<EditText>(R.id.editTextNewUserName).text.toString()
        val CusUserPassword = findViewById<EditText>(R.id.editTextNewUserPassword).text.toString()
        val CusPhoneNo = findViewById<EditText>(R.id.editTextPhoneNo).text.toString()


        val CusIsActive = if (findViewById<RadioButton>(findViewById<RadioGroup>(R.id.radioGroupActivity).checkedRadioButtonId).text.toString() == "Active") 0 else 1

        val message = findViewById<TextView>(R.id.textViewMessage)
        val validationResult = UserInputValidator().validateNewUserInput(CusfullName, CusEmail, CusPhoneNo, CusUserName, CusUserPassword)

        if (validationResult == "Valid") {
            // Save data
            val hashedPassword = PasswordHasher.hashPassword(CusUserPassword)
            val newUser = Customer(-1, CusfullName, CusEmail, CusPhoneNo, CusUserName, hashedPassword, CusIsActive)
            val mydatabase = DataBaseHelper(this)
            val result = mydatabase.addCustomer(newUser)

            when (result) {
                -1 -> message.text = "Error on creating a new account"
                -2 -> message.text = "Error can not open/create database"
                -3 -> message.text = "User name is already exist"
                else -> {
                    message.text = "Your details have been added to the database successfully"
                    findViewById<Button>(R.id.buttonSave).isEnabled = false
                }
            }
        } else {
            message.text = validationResult
        }
    }

}
