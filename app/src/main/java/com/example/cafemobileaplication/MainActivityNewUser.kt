package com.example.cafemobileaplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.cafemobileaplication.Model.Customer
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.PasswordHasher



class MainActivityNewUser  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user)
    }
    fun saveNewUserButton(view: View) {

        val CusfullName = findViewById<EditText>(R.id.editTextFullName).text.toString()
        val CusEmail  = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        val CusUserName  = findViewById<EditText>(R.id.editTextNewUserName).text.toString()
        val CusUserPassword  = findViewById<EditText>(R.id.editTextNewUserPassword).text.toString()
        val CusPhoneNo = findViewById<EditText>(R.id.editTextPhoneNo).text.toString()
        var CusIsActive = 0

        val message = findViewById<TextView>(R.id.textViewMessage)

        /*check Male or Female*/
        val rg = findViewById<RadioGroup>(R.id.radioGroupActivity)
        val rb = findViewById<RadioButton>(rg.checkedRadioButtonId)
        if(rb.text.toString() == "Male")
            CusIsActive = 0
        else CusIsActive = 1

        // if not empty, covert age to integer,  otherwise zero
       // val userAge = if(age.isEmpty()) 0 else age.toInt()

        if(CusfullName.isEmpty() ) // First and last name are required
        // Toast.makeText(this,"First name and last name are required!",Toast.LENGTH_LONG).show()
            message.text = "First name and last name are required!"
        else if(CusUserName.isEmpty() || CusUserPassword.isEmpty() ) // // User name and password are required
        //  Toast.makeText(this,"User name and Password are required!",Toast.LENGTH_LONG).show()
            message.text = "User name and Password are required!"
        else { // Save data
            // Create object
            // Hash the password using PasswordHasher
            val hashedPassword = PasswordHasher.hashPassword(CusUserPassword)
            val hashedPassword2 = PasswordHasher.hashPassword(CusUserPassword)
            println("Hashed Password: $hashedPassword")

            // Create object with hashed password
            val newUser = Customer(-1, CusfullName, CusEmail, CusPhoneNo, CusUserName, hashedPassword, CusIsActive)
            //save data
            val mydatabase = DataBaseHelper(this)
            val result = mydatabase.addCustomer(newUser)

            when(result) {

                -1 -> message.text = "Error on creating new account"
                -2 -> message.text = "Error can not open/create database"
                -3 -> message.text = "User name is already exist"
                else ->  {
                    message.text = "Your details has been add to the database successfully "
                    findViewById<Button>(R.id. buttonSave).isEnabled = false
                }
            }
        }
    }
}
