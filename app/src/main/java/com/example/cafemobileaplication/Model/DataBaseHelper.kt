package com.example.cafemobileaplication.Model


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/* Database Config*/
private val DataBaseName = "CourseWorkDB.db"
private val ver : Int = 1

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,DataBaseName,null ,ver) {
    /* Customer Table */
    private val CustomerTableName = "TCustomer"

    private val Customer_Column_ID = "CusId"
    private val Customer_Column_FullName = "CusFullName"
    private val Customer_Column_Email = "CusEmail"
    private val Customer_Column_PhoneNo = "CusPhoneNo"
    private val Customer_Column_UserName = "CusUserName"
    private val Customer_Column_Password = "CusPassword"
    private val Customer_Column_IsActive = "CusIsActive"

    /* Admin Table
    private val AdminTableName = "TAdmin"
    private val Admin_Column_ID = "AdminId"
    private val Admin_Column_FullName = "AdminFullName"
    private val Admin_Column_Email = "AdminEmail"
    private val Admin_Column_PhoneNo = "AdminPhoneNo"
    private val Admin_Column_UserName = "AdminUserName"
    private val Admin_Column_Password = "AdminPassword"
    private val Admin_Column_IsActive = "AdminIsActive"*/

    // Define other tables here
    // ..............................................................................
    // This is called the first time a database is accessed
    // Create a new database if not exist
    override fun onCreate(db: SQLiteDatabase?) {

        // Create Customer table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + CustomerTableName + "(" + Customer_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Customer_Column_FullName + " TEXT NOT NULL, " +
                    Customer_Column_Email + " TEXT NOT NULL, " + Customer_Column_PhoneNo + " TEXT NOT NULL, "  + Customer_Column_UserName + " TEXT NOT NULL, " +
                    Customer_Column_Password + " TEXT NOT NULL, " + Customer_Column_IsActive + " INTEGER NOT NULL )"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}
//..........................................................
        /*Create Admin table
        sqlCreateStatement = "CREATE TABLE $AdminTableName ( $Admin_Column_ID INTEGER PRIMARY KEY AUTOINCREMENT, $Admin_Column_FullName TEXT NOT NULL, " +
                " $Admin_Column_Email TEXT NOT NULL, $Admin_Column_PhoneNo TEXT NOT NULL, $Admin_Column_UserName TEXT NOT NULL, " +
                " $Admin_Column_Password TEXT NOT NULL, $Admin_Column_IsActive INTEGER NOT NULL )"

        db?.execSQL(sqlCreateStatement)*/
//..........................................................
//     Create other tables here
    }

    // This is called if the database ver. is changed
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    /**
     * return  1 : the new use has been add to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not Open/Create database
     * return -3 : user name is already exist
     *
     */
    //Chatgpt
    fun addCustomer(customer: Customer) : Int {

        val db: SQLiteDatabase
        val isUserNameAlreadyExists = checkUserName(customer) // check if the username is already exist in the database
        if(isUserNameAlreadyExists < 0)
            return isUserNameAlreadyExists

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Customer_Column_FullName, customer.cusFullName)
        cv.put(Customer_Column_Email, customer.cusEmail)
        cv.put(Customer_Column_PhoneNo, customer.cusPhoneNo)
        cv.put(Customer_Column_UserName, customer.userName.lowercase())
        cv.put(Customer_Column_Password, customer.password)
        cv.put(Customer_Column_IsActive, customer.isActive)

        val success  =  db.insert(CustomerTableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1
    }


    private fun checkUserName(customer: Customer): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = customer.userName.lowercase()

        val sqlStatement = "SELECT * FROM $CustomerTableName WHERE $Customer_Column_UserName = ?"
        val param = arrayOf(userName)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error the user name is already exist
        }

        cursor.close()
        db.close()
        return 0 //User not found

    }

    fun getCustomer(customer: Customer) : Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = customer.userName.lowercase()
        val userPassword = customer.password
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"

        val sqlStatement = "SELECT * FROM $CustomerTableName WHERE $Customer_Column_UserName = ? AND $Customer_Column_Password = ?"
        val param = arrayOf(userName,userPassword)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -1 //User not found

    }


}


