package com.example.shoppingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val productData = intent.getParcelableExtra<Product>("products")

        val imageView: ImageView = findViewById<ImageView>(R.id.productImage)
        Picasso.get().load(productData?.thumbnail).into(imageView);

        findViewById<TextView>(R.id.productName).text = productData?.title
        findViewById<TextView>(R.id.productPrice).text = "₹ ${productData?.price}"
        findViewById<TextView>(R.id.productBrand).text = "${productData?.brand} | ${productData?.category}"
        findViewById<TextView>(R.id.productRating).text = "⭐ ${productData?.rating}"
        findViewById<TextView>(R.id.productDetails).text = productData?.description


//        val title = intent.getStringExtra("name")
//        val review = intent.getParcelableExtra<Review>("review")
//
//        findViewById<TextView>(R.id.productName).text = title
//        val id = findViewById<TextView>(R.id.productReview)
//        id.text= "${review?.comment}\n${review?.rating}\n${review?.reviewerName}\n${review?.reviewerEmail}\n${review?.comment}"

    }
}