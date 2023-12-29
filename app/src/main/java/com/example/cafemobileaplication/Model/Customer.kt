package com.example.cafemobileaplication.Model

data class Customer (val cusId: Int, var cusFullName: String, var cusEmail: String, var cusPhoneNo: String,
                val userName: String, val password: String, val isActive: Int) {
}