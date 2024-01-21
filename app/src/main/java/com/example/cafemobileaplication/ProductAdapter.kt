package com.example.cafemobileaplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Product

class ProductAdapter(context: Context, resource: Int, private val productList: List<Product>, private val dbHelper: DataBaseHelper) :
    ArrayAdapter<Product>(context, resource, productList) {

    var addToCartListener: ((Product) -> Unit)? = null
    //private val cartItems = mutableListOf<Product>()

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.single_item, parent, false)

        val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        val productPriceTextView: TextView = itemView.findViewById(R.id.productPriceTextView)
        val productImageView: ImageView = itemView.findViewById(R.id.productImageViewSingleItem)
        val productAvailableTextView: TextView = itemView.findViewById(R.id.productAvailableTextView)

        val product = productList[position]


        productNameTextView.text = product.productName
        productPriceTextView.text = "Price: ${product.productPrice}"
        productAvailableTextView.text = "Available: ${product.productAvailable}"

        // Assuming productImage is a ByteArray
        val imageByteArray = product.productImage
        if (imageByteArray != null) {
            // Convert the ByteArray to a Bitmap
            val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            // Set the Bitmap to the ImageView
            productImageView.setImageBitmap(bitmap)
        }
        //NEW

        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
        addToCartButton.tag = getItem(position)  // Set the product as the tag of the button

        addToCartButton.setOnClickListener {
            val selectedProduct = it.tag as Product  // Retrieve the product from the button's tag
            addToCart(selectedProduct)


            // Inside the getView method of ProductAdapter
     /*   val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
        addToCartButton.tag = getItem(position)

        addToCartButton.setOnClickListener {
            // Get the position of the item in the list
            val itemPosition = position

            // Get the corresponding product
            val selectedProduct = productList[itemPosition]

            // Call a function to handle adding the product to the cart
            addToCart(selectedProduct)*/
        }

        return itemView
    }

    //NEW
    fun addToCart(product: Product) {
        val existingCartItem = dbHelper.getCartItemByProductId(product.productId)
        if (existingCartItem != null) {
            dbHelper.updateCartItemQuantity(existingCartItem.cartId, existingCartItem.cartProductQuantity + 1)
        } else {
            addToCartListener?.invoke(product)
            val toast = Toast.makeText(context, "Item added to cart successfully!", Toast.LENGTH_LONG)
            toast.show()
           // notifyDataSetChanged()
        }

    }
    // Setter method for the listener
    fun setOnAddToCartListener(listener: (Product) -> Unit) {
        addToCartListener = listener
    }

}
