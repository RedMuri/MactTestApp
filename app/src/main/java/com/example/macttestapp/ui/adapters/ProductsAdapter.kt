package com.example.macttestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.macttestapp.databinding.RvItemProductBinding
import com.example.macttestapp.domain.model.Product
import com.squareup.picasso.Picasso

class ProductsAdapter() :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {

    var onProductClickListener: ((Product) -> Unit)? = null

    class ProductViewHolder(val binding: RvItemProductBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = RvItemProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvProductTitle.text = title
                tvProductPrice.text = price.toString()
                Picasso.get().load(thumbnail).into(ivProductThumbnail)
                root.setOnClickListener {
                    onProductClickListener?.invoke(this)
                }
            }
        }
    }
}
