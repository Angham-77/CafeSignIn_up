package com.example.cafemobileaplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cafemobileaplication.Model.Cart
import com.example.cafemobileaplication.Model.DataBaseHelper

class CartAdapter(context: Context, resource: Int, private val cartItems: MutableList<Cart>) :
    ArrayAdapter<Cart>(context, resource, cartItems) {
    interface OnItemDeletedListener {
        fun onItemDeleted()
    }

    var onItemDeletedListener: OnItemDeletedListener? = null

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.single_cart_item, parent, false)

        val productNameTextView: TextView = view.findViewById(R.id.CartproductNameTextView)
        val productPriceTextView: TextView = view.findViewById(R.id.CartproductPriceTextView)
        val productImageView: ImageView = view.findViewById(R.id.productImageViewSingleCartItem)

        val cartProduct = getItem(position)

        productNameTextView.text = cartProduct?.cartProductName
        productPriceTextView.text = "Price: ${cartProduct?.cartProductPrice}"

        // Assuming productImage is a ByteArray
        val imageByteArray = cartProduct?.cartProductImage
        if (imageByteArray != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            productImageView.setImageBitmap(bitmap)
        }

        val deleteIcon: ImageView = view.findViewById(R.id.deleteIcon)
        deleteIcon.setOnClickListener {
            val itemToDelete = getItem(position)
            if (itemToDelete != null) {
                deleteItemFromDatabase(itemToDelete)
                remove(itemToDelete)
                notifyDataSetChanged()
            }
        }

        return view
    }

    private fun deleteItemFromDatabase(cartItem: Cart) {
        val dbHelper = DataBaseHelper(context)
        dbHelper.deleteCartItem(cartItem.cartId)
    }
}
