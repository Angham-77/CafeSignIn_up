package com.example.cafemobileaplication.Model

import at.favre.lib.crypto.bcrypt.BCrypt

class PasswordHasher {
        companion object {
            private const val COST = 12 // You can adjust the cost factor based on your security requirements

            fun hashPassword(plainPassword: String): String {
                return BCrypt.withDefaults().hashToString(COST, plainPassword.toCharArray())
            }

            fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
                return BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword).verified
            }
        }
}