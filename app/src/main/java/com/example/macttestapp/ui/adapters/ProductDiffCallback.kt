package com.example.macttestapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.macttestapp.domain.model.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}