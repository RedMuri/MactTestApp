package com.example.macttestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.macttestapp.databinding.RvItemQuoteBinding
import com.example.macttestapp.domain.model.Quote
import javax.inject.Inject

class QuotesAdapter @Inject constructor() : ListAdapter<Quote, QuotesAdapter.QuoteViewHolder>(QuoteDiffCallback()) {

    var onReachEndListener: ((Int) -> Unit)? = null

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
                if (itemCount - position == 1)
                    onReachEndListener?.invoke(itemCount)
                tvQuoteAuthor.text = String.format("—%s", author)
                tvQuoteText.text = String.format("«%s»", text)
            }
        }
    }
}
