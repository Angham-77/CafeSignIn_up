import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.example.cafemobileaplication.Model.Product
import com.example.cafemobileaplication.R

class CartAdapter(context: Context, private val layoutResourceId: Int, data: List<Product>) : ArrayAdapter<Product>(context, layoutResourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        val holder: CartItemHolder

        if (row == null) {
            val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            row = inflater.inflate(layoutResourceId, parent, false)

            holder = CartItemHolder()
            holder.productNameTextView = row.findViewById(R.id.CartproductNameTextView)
            holder.productPriceTextView = row.findViewById(R.id.CartproductPriceTextView)
            holder.quantitySpinner = row.findViewById(R.id.quantitySpinner)
            holder.deleteIcon = row.findViewById(R.id.deleteIcon)

            row.tag = holder
        } else {
            holder = row.tag as CartItemHolder
        }

        val cartItem = getItem(position)

        // Ensure that cartItem is not null
        if (cartItem != null) {
            // Set data to views
            holder.productNameTextView.text = cartItem.productName
            holder.productPriceTextView.text = "Price: ${cartItem.productPrice}"
            // Set up the spinner with quantity options and handle its selection

            val quantityOptions = arrayListOf<String>()
            for (i in 1..10) {
                quantityOptions.add(i.toString())
            }

            val qtyAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, quantityOptions)
            qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.quantitySpinner.adapter = qtyAdapter

            // Set other data and click listeners as needed
        }

        // Use the safe call operator to handle the nullable case
        return row ?: View(context)
    }
    private class CartItemHolder {
        lateinit var productNameTextView: TextView
        lateinit var productPriceTextView: TextView
        lateinit var quantitySpinner: Spinner
        lateinit var deleteIcon: ImageView
    }
}
