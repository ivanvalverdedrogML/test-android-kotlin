package com.example.test_android_kotlin.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_android_kotlin.data.model.Model
import com.example.test_android_kotlin.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductAdapter(private val productList: ArrayList<Model.Product>): RecyclerView.Adapter<ProductAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(var view: ItemProductBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemProductBinding.inflate(inflater, parent, false)
        return ProductListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList[position]

        holder.view.apply {
            productName.text = product.title
            productId.text = product.id
            productDetail.text = product.title
            productPrice.text = "$ " + product.price.toString()

            Picasso.get().load(product.pictures[0].secure_url).fit().centerCrop().into(imageView)
        }
    }

    fun updateList(newProductList: ArrayList<Model.Product>){
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = productList.size
}
