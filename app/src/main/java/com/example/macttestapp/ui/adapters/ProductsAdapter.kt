package com.example.macttestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.macttestapp.R
import com.example.macttestapp.databinding.RvItemProductBinding
import com.example.macttestapp.domain.model.Product
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ProductsAdapter @Inject constructor() :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {

    var onProductClickListener: ((Product) -> Unit)? = null
    var onReachEndListener: ((Int) -> Unit)? = null

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
                if (itemCount - position == 1)
                    onReachEndListener?.invoke(itemCount)
                tvProductTitle.text = title
                tvProductPrice.text = String.format("%s$", price)
                Picasso.get().load(thumbnail).placeholder(R.drawable.progress_animation).into(ivProductThumbnail)
                root.setOnClickListener {
                    onProductClickListener?.invoke(this)
                }
            }
        }
    }
}
