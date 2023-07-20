package com.example.macttestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.macttestapp.databinding.RvItemQuoteBinding
import com.example.macttestapp.domain.model.Quote

class QuotesAdapter() : ListAdapter<Quote, QuotesAdapter.QuoteViewHolder>(QuoteDiffCallback()) {

    class QuoteViewHolder(val binding: RvItemQuoteBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = RvItemQuoteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        with(holder.binding) {
            with(currentList[position]) {
                tvQuoteAuthor.text = author
                tvQuoteText.text = text
            }
        }
    }
}
