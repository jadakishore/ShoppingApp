package com.example.shoppingapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList: List<Product>): RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem= dataList[position]

        holder.pname.text= currentItem.title

        Picasso.get().load(currentItem.thumbnail).into(holder.image);

        holder.pricE.text = "₹ ${currentItem.price.toString()}\""

        holder.viewMore.setOnClickListener {

            val intent = Intent(context, ProductDetailsActivity::class.java)

            intent.putExtra("products", currentItem)

//            intent.putExtra("name", currentItem.title)
//            intent.putParcelableArrayListExtra("review", ArrayList(currentItem.reviews))

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val pname: TextView = itemView.findViewById<TextView>(R.id.productName)
        val pricE: TextView = itemView.findViewById<TextView>(R.id.productPrice)
        val viewMore: TextView = itemView.findViewById(R.id.viewMore)
    }
}