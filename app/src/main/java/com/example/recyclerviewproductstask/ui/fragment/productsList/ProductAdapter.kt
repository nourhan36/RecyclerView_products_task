package com.example.recyclerviewproductstask.ui.fragment.productsList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.recyclerviewproductstask.R
import com.example.recyclerviewproductstask.api.model.Product
import com.example.recyclerviewproductstask.databinding.ItemProductBinding

class ProductAdapter(private var productList: List<Product?>?) :
    Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList?.get(position) ?: return
        holder.bind(item)
    }

    override fun getItemCount(): Int = productList?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(products: List<Product?>?) {
        productList = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(val itemBinding: ItemProductBinding) : ViewHolder(itemBinding.root) {
        fun bind(product: Product?) {
            itemBinding.apply {
                productName.text = product?.name
                productDescription.text = product?.description
                val newPrice = product?.price?.let { price ->
                    val discount = product.discountPercentage ?: 0.0
                    price - (price * discount / 100)
                }
                productPrice.text = "EGP ${String.format("%.2f", newPrice)}"
                productOldPrice.text = product?.price?.let { "EGP ${String.format("%.2f", it)}" }
                reviewValueTv.text = "Review (${product?.rating})"
                Glide.with(itemView)
                    .load(product?.imageUrl?.firstOrNull()) // Load the first image URL or null if the list is empty
                    .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image resource
                    .into(productImg) // ImageView to load the image into
            }
        }
    }

}