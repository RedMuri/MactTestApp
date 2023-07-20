package com.example.macttestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.macttestapp.databinding.FragmentQuotesBinding
import com.example.macttestapp.domain.model.Quote
import com.example.macttestapp.ui.adapters.QuotesAdapter


class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding: FragmentQuotesBinding
        get() = _binding ?: throw RuntimeException("FragmentQuotesBinding == null")

    private val adapterEateries by lazy {
        QuotesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvQuotes.adapter = adapterEateries
        val testQuotes = listOf(
            Quote(1, "«Один»", "quote text"),
            Quote(2, "«Два»", "quote text"),
            Quote(3, "«Три»", "quote text"),
            Quote(4, "«Четыре»", "quote text"),
            Quote(5, "«Пять»", "quote text"),
        )
        adapterEateries.submitList(testQuotes)
    }
}