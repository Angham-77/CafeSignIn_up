package com.example.cafemobileaplication

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.cafemobileaplication.Model.Product

class ProductAdapter(context: Context, resource: Int, private val productList: List<Product>) :
    ArrayAdapter<Product>(context, resource, productList) {

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

       val addToOrderButton: Button = itemView.findViewById(R.id.addToCartBtn)
        addToOrderButton.setOnClickListener {
            // Handle the click event, e.g., add the selected product to the order table
            val product = productList[position]
            // Call a method to add the product to the order table
            addToOrder(product)
        }
        return itemView
    }

    private fun addToOrder(product: Product) {
        // Implement the logic to add the selected product to the order table
        // You can use the DatabaseHelper to perform the database operation
        // For example, dbHelper.addToOrder(product)
    }


}
