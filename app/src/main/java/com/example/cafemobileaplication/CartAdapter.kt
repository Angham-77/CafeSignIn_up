package com.example.cafemobileaplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.example.cafemobileaplication.Model.Cart
import com.example.cafemobileaplication.Model.DataBaseHelper

class CartAdapter(context: Context, resource: Int, private val cartItems: MutableList<Cart>, private val dbHelper: DataBaseHelper ) :
    ArrayAdapter<Cart>(context, resource, cartItems) {
    interface OnItemDeletedListener {
        fun onItemDeleted()
    }
    var onItemDeletedListener: OnItemDeletedListener? = null
    interface OnPriceUpdateListener {
        fun onPriceUpdated()
    }
    var onPriceUpdateListener: OnPriceUpdateListener? = null


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
        //new
        val quantitySpinner: Spinner = view.findViewById(R.id.quantitySpinner)
        val quantities = (1..10).toList() // Example quantity range
        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, quantities)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        quantitySpinner.adapter = arrayAdapter




        val currentQuantity = cartProduct?.cartProductQuantity ?: 1
        quantitySpinner.setSelection(currentQuantity - 1) // Assuming quantity starts from 1

        quantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val selectedQuantity = pos + 1
                cartProduct?.cartProductQuantity = selectedQuantity
                updateCartItemQuantity(cartProduct)
                onPriceUpdateListener?.onPriceUpdated()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //

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
    private fun updateCartItemQuantity(cartItem: Cart?) {
        cartItem?.let {
            val dbHelper = DataBaseHelper(context)
            dbHelper.updateCartItemQuantity(it.cartId, it.cartProductQuantity)
        }
    }

    private fun deleteItemFromDatabase(cartItem: Cart) {
        val dbHelper = DataBaseHelper(context)
        dbHelper.deleteCartItem(cartItem.cartId)
    }
}
