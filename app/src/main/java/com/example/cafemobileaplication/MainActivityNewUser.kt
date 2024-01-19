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
        var CusIsActive = 0

        val message = findViewById<TextView>(R.id.textViewMessage)

       //CHECK??? /*check Male or Female*/
        val rg = findViewById<RadioGroup>(R.id.radioGroupActivity)
        val rb = findViewById<RadioButton>(rg.checkedRadioButtonId)
        if (rb.text.toString() == "Male")
            CusIsActive = 0
        else CusIsActive = 1

        //chatGPT
        var isValid = true  // introduce a boolean flag

        if (CusfullName.isEmpty()) {
            message.text = "Full name is required!"
            isValid = false
        } else if (CusfullName.length < 3 || CusfullName.length > 50) {
            message.text = "Full name must be between 3 and 50 characters!"
            isValid = false
        }

        if (CusEmail.isEmpty()){
            message.text = "Email is required!"
            isValid = false
        } else {
            val emailPattern = Patterns.EMAIL_ADDRESS
            if (!emailPattern.matcher(CusEmail).matches()) {
                message.text = "Invalid email format.!"
                isValid = false
            }
        }

        if (CusPhoneNo.isEmpty()){
            message.text = "Phone No is required!"
            isValid = false
        } else {
            val phonePattern = Pattern.compile("\\d{11}")
            if (!phonePattern.matcher(CusPhoneNo).matches()) {
                message.text = "Invalid phone number format. It should have 10 digits.!"
                isValid = false
            }
        }

        if (CusUserName.isEmpty() || CusUserPassword.isEmpty()) {
            message.text = "Username and Password are required!"
            isValid = false
        } else if (CusUserName.length < 4 || CusUserName.length > 20) {
            message.text = "Username must be between 5 and 20 characters."
            isValid = false
        }

        if (isValid) {
            // Save data
            // Create object
            // Hash the password using PasswordHasher
            val hashedPassword = PasswordHasher.hashPassword(CusUserPassword)
            println("Hashed Password: $hashedPassword")

            // Create object with hashed password
            val newUser = Customer(
                -1,
                CusfullName,
                CusEmail,
                CusPhoneNo,
                CusUserName,
                hashedPassword,
                CusIsActive
            )
            //save data
            val mydatabase = DataBaseHelper(this)
            val result = mydatabase.addCustomer(newUser)

            when (result) {
                -1 -> message.text = "Error on creating a new account"
                -2 -> message.text = "Error can not open/create database"
                -3 -> message.text = "User name is already exist"
                else -> {
                    message.text = "Your details have been added to the database successfully "
                    findViewById<Button>(R.id.buttonSave).isEnabled = false
                }
            }
        }

    }
}
