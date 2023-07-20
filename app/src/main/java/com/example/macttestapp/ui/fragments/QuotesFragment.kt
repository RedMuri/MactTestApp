package com.example.macttestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.macttestapp.databinding.FragmentQuotesBinding
import com.example.macttestapp.domain.model.Quote
import com.example.macttestapp.ui.adapters.QuotesAdapter
import com.example.macttestapp.ui.state.QuotesScreenState
import com.example.macttestapp.ui.viewmodel.QuotesViewModel
import kotlinx.coroutines.launch


class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding: FragmentQuotesBinding
        get() = _binding ?: throw RuntimeException("FragmentQuotesBinding == null")

    private val adapterEateries by lazy {
        QuotesAdapter()
    }

    private val quotesViewModel by lazy {
        ViewModelProvider(this)[QuotesViewModel::class.java]
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
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            quotesViewModel.quotesScreenState.collect { state ->
                when (state) {
                    is QuotesScreenState.Content -> {
                        adapterEateries.submitList(state.quotes)
                    }

                    is QuotesScreenState.Loading -> {

                    }

                    is QuotesScreenState.Error -> {

                    }
                }
            }
        }

    }

    private fun setupRecyclerView() {
        binding.rvQuotes.adapter = adapterEateries
    }
}