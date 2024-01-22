package com.example.cafemobileaplication

import android.util.Patterns
import java.util.regex.Pattern

class UserInputValidator {
    //chatgpt
        fun validateNewUserInput(fullName: String, email: String, phoneNo: String, userName: String, userPassword: String): String {
            if (fullName.isEmpty()) {
                return "Full name is required!"
            } else if (fullName.length < 3 || fullName.length > 50) {
                return "Full name must be between 3 and 50 characters!"
            }

            if (email.isEmpty()) {
                return "Email is required!"
            } else {
                val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")
                if (!emailPattern.matches(email)) {
                    return "Invalid email format.!"
                }
            }


            if (phoneNo.isEmpty()) {
                return "Phone No is required!"
            } else {
                val phonePattern = Pattern.compile("\\d{11}")
                if (!phonePattern.matcher(phoneNo).matches()) {
                    return "Invalid phone number format. It should have 10 digits.!"
                }
            }

            if (userName.isEmpty() || userPassword.isEmpty()) {
                return "Username and Password are required!"
            } else if (userName.length < 4 || userName.length > 20) {
                return "Username must be between 5 and 20 characters."
            }

            return "Valid"
        }

}