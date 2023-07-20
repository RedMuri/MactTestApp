package com.example.macttestapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.macttestapp.domain.model.Quote

class QuoteDiffCallback : DiffUtil.ItemCallback<Quote>() {

    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean =
        oldItem == newItem
}