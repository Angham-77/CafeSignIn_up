package com.example.cafemobileaplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.cafemobileaplication.Model.DataBaseHelper
import com.example.cafemobileaplication.Model.Product

class MainActivityProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_listview)

        val dbHelper = DataBaseHelper(this)
        val productList = dbHelper.getAllProducts()

        val listView: ListView = findViewById(R.id.listView)

        // Inside your MainActivity
      /*  val adapter = ArrayAdapter<Product>(
            this,
            R.layout.single_item,
            R.id.productNameTextView,
            productList
        )*/
        val adapter = ProductAdapter(this, R.layout.single_item, productList)
        listView.adapter = adapter //modify this

        listView.adapter = adapter
    }


}
