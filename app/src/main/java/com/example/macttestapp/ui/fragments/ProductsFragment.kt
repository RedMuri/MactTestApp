package com.example.macttestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.macttestapp.databinding.FragmentProductsBinding
import com.example.macttestapp.ui.adapters.ProductsAdapter
import com.example.macttestapp.ui.state.ProductsScreenState
import com.example.macttestapp.ui.state.QuotesScreenState
import com.example.macttestapp.ui.viewmodel.ProductsViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentProductsBinding == null")

    private val adapterProducts by lazy {
        ProductsAdapter()
    }

    private val productsViewModel by lazy {
        ViewModelProvider(this)[ProductsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            productsViewModel.productsScreenState.collect { state ->
                when (state) {
                    is ProductsScreenState.Content -> {
                        adapterProducts.submitList(state.products)
                    }

                    is ProductsScreenState.Loading -> {

                    }

                    is ProductsScreenState.Error -> {

                    }
                }
            }
        }

    }

    private fun setupRecyclerView() {
        binding.rvItems.adapter = adapterProducts
        adapterProducts.onProductClickListener = { product ->
            binding.llItemDetails.visibility = View.VISIBLE
            binding.tvItemDescription.text = product.description
            Picasso.get().load(product.images.random().ifEmpty { product.thumbnail })
                .into(binding.ivItemImage)
        }
        binding.llItemDetails.setOnClickListener {
            it.visibility = View.GONE
        }
    }
}