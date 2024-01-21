package com.example.cafemobileaplication.Model


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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

    /*Craete cart table*/
    private val CartTableName ="TCart"

    private val Cart_Column_ID = "CartId"
    private val Cart_Column_ProdID = "ProdId"
    private val Cart_Column_Name = "ProdName"
    private val Cart_Column_Image = "ProdImage"
    private val Cart_Column_Price = "ProdPrice"
    private val Cart_Column_Quantity = "ProdQuantity"

    /*Create Order Table*/
    private val OrderTableName = "TOrder"

    private val Order_Column_ID = "OrderId"
    private val Order_Column_CusId = "CusId"
    private val Order_Column_OrderDate = "OrderDate"
    private val Order_Column_OrderTime = "OrderTime"
    private val Order_Column_OrderStatus = "OrderStatus"

    /*Create ORDER DETAILS Table */

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


    /*feedback table*/

    private val FeedbackTableName = "TFeedback"

    private val Feedback_Column_ID = "FeedbackId"
    private val Feedback_Column_UserId = "CusId"
    private val Feedback_Column_FeedbackContent = "FeedbackContent"
    private val Feedback_Column_Rating = "Rating"


    // Admin Table
    private val AdminTableName = "TAdmin"
    private val Admin_Column_ID = "AdminId"
    private val Admin_Column_FullName = "AdminFullName"
    private val Admin_Column_Email = "AdminEmail"
    private val Admin_Column_PhoneNo = "AdminPhoneNo"
    private val Admin_Column_UserName = "AdminUserName"
    private val Admin_Column_Password = "AdminPassword"
    private val Admin_Column_IsActive = "AdminIsActive"

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
       // Create Admin table
        try {
           var sqlCreateStatement = "CREATE TABLE $AdminTableName ( $Admin_Column_ID INTEGER PRIMARY KEY AUTOINCREMENT, $Admin_Column_FullName TEXT NOT NULL, " +
                    " $Admin_Column_Email TEXT NOT NULL, $Admin_Column_PhoneNo TEXT NOT NULL, $Admin_Column_UserName TEXT NOT NULL, " +
                    " $Admin_Column_Password TEXT NOT NULL, $Admin_Column_IsActive INTEGER NOT NULL )"
            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}


//..........................................................
        //Create product table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + ProductTableName + "(" + Product_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Product_Column_Name + " TEXT NOT NULL, " +
                    Product_Column_Image+ "BLOB" + Product_Column_Price+ " DOUBLE NOT NULL, "  + Product_Column_Available + " INTEGER NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}



        //Create Cart table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + CartTableName + "(" + Cart_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Cart_Column_ProdID + " TEXT " +   Cart_Column_Name + " TEXT NOT NULL, " +
                    Cart_Column_Image+ "BLOB" + Cart_Column_Price+ " DOUBLE NOT NULL, "  + Cart_Column_Quantity + " INTEGER NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}

        //Create order table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + OrderTableName + "(" + Order_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Order_Column_CusId + " INTEGER NOT NULL, " +
                    Order_Column_OrderDate+ " TEXT NOT NULL " + Order_Column_OrderTime + " TEXT NOT NULL, "  + Order_Column_OrderStatus + " INTEGER NOT NULL)"

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
                    Payment_Column_PaymentType+ " INTEGER NOT NULL " + Payment_Column_Amount + " REAL NOT NULL, "  + Payment_Column_PaymentDate + " TEXT NOT NULL)"

            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}

        // Create Feedback table
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + FeedbackTableName + "(" + Feedback_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  Feedback_Column_UserId + " INTEGER " +
                    Feedback_Column_FeedbackContent + " TEXT NOT NULL," + Feedback_Column_Rating +  " REAL NOT NULL)"

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
    @SuppressLint("Range")
    fun getAllCartItems(): List<Cart> {
        val cartItemList = mutableListOf<Cart>()
        val db: SQLiteDatabase

        try {
            db = this.readableDatabase
        } catch (e: SQLiteException) {
            // Handle the exception as needed
            Log.e("DataBaseHelper", "Error opening readable database: ${e.message}")
            return emptyList()
        }

        val sqlStatement = "SELECT * FROM $CartTableName"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        while (cursor.moveToNext()) {
            val CartItemName = cursor.getString(cursor.getColumnIndex(Cart_Column_Name))
            val CartItemImage = cursor.getBlob(cursor.getColumnIndex(Cart_Column_Image))
            val CartItemPrice = cursor.getDouble(cursor.getColumnIndex(Cart_Column_Price))
            val CartItemQuantity = cursor.getInt(cursor.getColumnIndex(Cart_Column_Quantity))

            val cart = Cart(-1, 0, CartItemName,CartItemImage, CartItemPrice, CartItemQuantity )
            cartItemList.add(cart)
        }

        cursor.close()
        db.close()
        Log.d("DataBaseHelper", "getAllCartItems: Successfully retrieved ${cartItemList.size} cart items.")

        return cartItemList
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
   fun addFeedback(feedback: Feedback): Int {
       val db: SQLiteDatabase
       try {
           db = this.writableDatabase
       } catch (e: SQLiteException) {
           return -2
       }

       val cv: ContentValues = ContentValues()

       // Use Feedback_Column_FeedbackContent instead of FeedbackTableName
       cv.put(Feedback_Column_FeedbackContent, feedback.feedbackText)
       cv.put(Feedback_Column_Rating, feedback.rating)

       val success = db.insert(FeedbackTableName, null, cv)

       db.close()
       if (success.toInt() == -1) return success.toInt() // Error, adding new feedback
       else return success.toInt() // 1
   }
    fun addItemToCart(cart: Cart): Int {
        val db: SQLiteDatabase
        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            Log.e("DataBaseHelper", "Error opening writable database: ${e.message}")
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Cart_Column_Name, cart.cartProductName)
        cv.put(Cart_Column_Image, cart.cartProductImage)
        cv.put(Cart_Column_Price, cart.cartProductPrice)
        cv.put(Cart_Column_Quantity, cart.cartProductQuantity)



        val success  =  db.insert(CartTableName, null, cv)

        db.close()
        if (success.toInt() == -1) {
            Log.e("DataBaseHelper", "addItemToCart: Error adding cart item to database.")

            return success.toInt() //Error, adding new user
        }
        else {
            Log.d("DataBaseHelper", "addItemToCart: Successfully added cart item to database.")
            return success.toInt() //1
        }

    }
    fun deleteCartItem(cartId: Int) {
        val db = this.writableDatabase
        try {
            // Delete the item from the cart table where the ID matches
            db.delete(CartTableName, "$Cart_Column_ID = ?", arrayOf(cartId.toString()))
        } catch (e: SQLiteException) {
            Log.e("DataBaseHelper", "Error deleting cart item: ${e.message}")
        } finally {
            db.close()
        }
    }
    fun clearCart() {
        val db = writableDatabase
        try {
            db.delete(CartTableName, null, null)
        } catch (e: SQLiteException) {
            Log.e("DataBaseHelper", "Error clearing cart: ${e.message}")
        } finally {
            db.close()
        }
    }
    fun updateCartItemQuantity(cartId: Int, quantity: Int) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Cart_Column_Quantity, quantity)
        db.update(CartTableName, contentValues, "$Cart_Column_ID = ?", arrayOf(cartId.toString()))
        db.close()
    }
    @SuppressLint("Range")
    fun getCartItemByProductId(productId: Int): Cart? {//needs modifications
        val db = this.readableDatabase
        var cartItem: Cart? = null

        val cursor = db.query(
            CartTableName, // Table name
            arrayOf(Cart_Column_ID, Cart_Column_Name, Cart_Column_Image, Cart_Column_Price, Cart_Column_Quantity), // Columns to return
            "$Cart_Column_ProdID = ?", // Selection criteria
            arrayOf(productId.toString()), // Selection arguments
            null, // Group by
            null, // Having
            null // Order by
        )

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(Cart_Column_ID))
            val name = cursor.getString(cursor.getColumnIndex(Cart_Column_Name))
            val image = cursor.getBlob(cursor.getColumnIndex(Cart_Column_Image))
            val price = cursor.getDouble(cursor.getColumnIndex(Cart_Column_Price))
            val quantity = cursor.getInt(cursor.getColumnIndex(Cart_Column_Quantity))

            cartItem = Cart(id, productId, name, image, price, quantity)
        }

        cursor.close()
        db.close()
        return cartItem
    }
    @SuppressLint("Range")
    fun calculateTotalPrice(): Double {
        val db = this.readableDatabase
        var totalPrice = 0.0

        val cursor = db.query(
            CartTableName, // Table name
            arrayOf(Cart_Column_Price, Cart_Column_Quantity), // Columns: price and quantity
            null, // Selection criteria (null for all items)
            null, // Selection arguments
            null, // Group by
            null, // Having
            null // Order by
        )

        while (cursor.moveToNext()) {
            val price = cursor.getDouble(cursor.getColumnIndex(Cart_Column_Price))
            val quantity = cursor.getInt(cursor.getColumnIndex(Cart_Column_Quantity))
            totalPrice += price * quantity
        }

        cursor.close()
        db.close()
        return totalPrice
    }



}


