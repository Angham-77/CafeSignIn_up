package com.example.cafemobileaplication.Model


import android.annotation.SuppressLint
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

    /*Craete product table*/
    private val ProductTableName ="TProduct"

    private val Product_Column_ID = "ProdId"
    private val Product_Column_Name = "ProdName"
    private val Product_Column_Image = "ProdImage"
    private val Product_Column_Price = "ProdPrice"
    private val Product_Column_Available = "ProdIsAvailable"

    /*Create Order Table*/
    private val OrderTableName = "TOrder"

    private val Order_Column_ID = "OrderId"
    private val Order_Column_CusId = "CusId"
    private val Order_Column_OrderDate = "OrderDate"
    private val Order_Column_OrderTime = "OrderTime"
    private val Order_Column_OrderStatus = "OrderStatus"

    /*Create Table */

    private val OrderDetailstableName = "TOrderDetails"

    private val OrderDetails_Column_ID = "OrderDetailsId"
    private val OrderDetails_Column_OrderId = "OrderId"
    private val OrderDetails_Column_ProductId = "ProductId"

    /*Create Payment details*/
    private val PaymentTableName = "TPayment"

    private val Payment_Column_ID = "PaymentId"
    private val Payment_Column_OrderId = "OrderId"
    private val Payment_Column_PaymentType = "PaymentType"
    private val Payment_Column_Amount = "Amount"
    private val Payment_Column_PaymentDate = "PaymentDate"


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
        //Create product table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + ProductTableName + "(" + Product_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Product_Column_Name + " TEXT NOT NULL, " +
                    Product_Column_Image+ "BLOB" + Product_Column_Price+ " DOUBLE NOT NULL, "  + Product_Column_Available + " INTEGER NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}

        //Create order table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + OrderTableName + "(" + Order_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Order_Column_CusId + " INTEGER NOT NULL, " +
                    Order_Column_OrderDate+ " TEXT NOT NULL " + Order_Column_OrderTime+ " TEXT NOT NULL, "  + Order_Column_OrderStatus + " INTEGER NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}

        //Create Order Details table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + OrderDetailstableName + "(" + OrderDetails_Column_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +  OrderDetails_Column_OrderId + " INTEGER NOT NULL, " +
                    OrderDetails_Column_ProductId + " INTEGER NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}

        //Create Payment  table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + PaymentTableName + "(" + Payment_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Payment_Column_OrderId + " INTEGER NOT NULL, " +
                    Payment_Column_PaymentType+ " INTEGER NOT NULL " + Payment_Column_Amount+ " REAL NOT NULL, "  + Payment_Column_PaymentDate + " TEXT NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}




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
    /*fun addToOrder(order: Order) : Int {

        val db: SQLiteDatabase
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

        cv.put

        val success  =  db.insert(CustomerTableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1
    }*/
    //chatgpt
    @SuppressLint("Range")
    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db: SQLiteDatabase

        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            // Handle the exception as needed
            return emptyList()
        }

        val sqlStatement = "SELECT * FROM $ProductTableName"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        while (cursor.moveToNext()) {
            val productName = cursor.getString(cursor.getColumnIndex(Product_Column_Name))
            val productImage = cursor.getBlob(cursor.getColumnIndex(Product_Column_Image))
            val productPrice = cursor.getDouble(cursor.getColumnIndex(Product_Column_Price))
            val productAvailable = cursor.getInt(cursor.getColumnIndex(Product_Column_Available))

            val product = Product(-1,productName, productImage, productPrice, productAvailable)
            productList.add(product)
        }

        cursor.close()
        db.close()

        return productList
    }
    //chatgpt
    fun addToOrder(product: Product, quantity: Int): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(OrderDetails_Column_ProductId, product.productId)
        cv.put(OrderDetails_Column_OrderId, getLatestOrderId())

        // Insert the product into the OrderDetails table
        val success = db.insert(OrderDetailstableName, null, cv)
        db.close()

        // Update the quantity in the Product table
        if (success != -1L) {
            updateProductQuantity(product.productId, quantity)
            return true
        }
        return false
    }

    private fun getLatestOrderId(): Int {
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT MAX($Order_Column_ID) FROM $OrderTableName", null)

        var latestOrderId = 0
        if (cursor.moveToFirst()) {
            latestOrderId = cursor.getInt(0)
        }

        cursor.close()
        db.close()

        return latestOrderId
    }

    private fun updateProductQuantity(productId: Int, quantity: Int) {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Product_Column_Available, getAvailableQuantity(productId) - quantity)

        db.update(ProductTableName, cv, "$Product_Column_ID=?", arrayOf(productId.toString()))
        db.close()
    }

    private fun getAvailableQuantity(productId: Int): Int {
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT $Product_Column_Available FROM $ProductTableName WHERE $Product_Column_ID=?", arrayOf(productId.toString()))

        var availableQuantity = 0
        if (cursor.moveToFirst()) {
            availableQuantity = cursor.getInt(0)
        }

        cursor.close()
        db.close()

        return availableQuantity
    }




}


