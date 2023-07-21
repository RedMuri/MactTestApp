package com.example.macttestapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.macttestapp.MactTestApp
import com.example.macttestapp.databinding.FragmentQuotesBinding
import com.example.macttestapp.ui.adapters.QuotesAdapter
import com.example.macttestapp.ui.state.QuotesScreenState
import com.example.macttestapp.ui.viewmodel.QuotesViewModel
import com.example.macttestapp.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding: FragmentQuotesBinding
        get() = _binding ?: throw RuntimeException("FragmentQuotesBinding == null")

    private val adapterQuotes by lazy {
        QuotesAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val quotesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[QuotesViewModel::class.java]
    }
    private val component by lazy {
        (requireActivity().application as MactTestApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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
        bindListeners()
    }

    private fun bindListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener{
            quotesViewModel.getQuotes()
        }
        adapterQuotes.onReachEndListener = {skip ->
            binding.pbBottom.visibility = View.VISIBLE
            quotesViewModel.getQuotes(skip = skip)
        }
    }

    private fun setupRecyclerView() {
        binding.rvQuotes.adapter = adapterQuotes
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            quotesViewModel.quotesScreenState.collect { state ->
                binding.pbBottom.visibility = View.GONE
                when (state) {
                    is QuotesScreenState.Content -> {
                        binding.errorLayout.visibility = View.GONE
                        binding.rvQuotes.visibility = View.VISIBLE
                        val quotes = adapterQuotes.currentList + state.quotes
                        adapterQuotes.submitList(quotes)
                    }

                    is QuotesScreenState.Loading -> {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }

                    is QuotesScreenState.Error -> {
                        binding.rvQuotes.visibility = View.GONE
                        binding.errorLayout.visibility = View.VISIBLE
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
        lifecycleScope.launch {
            quotesViewModel.isRefreshing.collect{
                binding.swipeRefreshLayout.isRefreshing = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}