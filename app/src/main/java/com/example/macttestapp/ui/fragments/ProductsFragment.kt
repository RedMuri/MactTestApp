package com.example.macttestapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.macttestapp.MactTestApp
import com.example.macttestapp.R
import com.example.macttestapp.databinding.FragmentProductsBinding
import com.example.macttestapp.ui.adapters.ProductsAdapter
import com.example.macttestapp.ui.state.ProductsScreenState
import com.example.macttestapp.ui.viewmodel.ProductsViewModel
import com.example.macttestapp.ui.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentProductsBinding == null")

    private val adapterProducts by lazy {
        ProductsAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val productsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProductsViewModel::class.java]
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
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        bindListeners()
    }

    private fun bindListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            productsViewModel.getProducts()
        }
        binding.llItemDetails.setOnClickListener {
            it.visibility = View.GONE
        }
        adapterProducts.onProductClickListener = { product ->
            binding.llItemDetails.visibility = View.VISIBLE
            binding.tvItemDescription.text = product.description
            Picasso.get().load(product.images.random().ifEmpty { product.thumbnail })
                .placeholder(R.drawable.progress_animation)
                .into(binding.ivItemImage)
        }
        adapterProducts.onReachEndListener = { skip ->
            binding.pbBottom.visibility = View.VISIBLE
            productsViewModel.getProducts(skip = skip)
        }
    }

    private fun setupRecyclerView() {
        binding.rvProducts.adapter = adapterProducts
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), getColumnCount())
    }

    private fun getColumnCount(): Int {
        val metrics = requireActivity().resources.displayMetrics
        val width = metrics.widthPixels / metrics.density.toInt()
        return if (width / 250 > 2) width / 250 else 2
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            productsViewModel.productsScreenState.collect { state ->
                binding.pbBottom.visibility = View.GONE
                when (state) {
                    is ProductsScreenState.Content -> {
                        binding.errorLayout.visibility = View.GONE
                        binding.rvProducts.visibility = View.VISIBLE
                        val products = adapterProducts.currentList + state.products
                        adapterProducts.submitList(products)
                    }

                    is ProductsScreenState.Loading -> {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }

                    is ProductsScreenState.Error -> {
                        binding.rvProducts.visibility = View.GONE
                        binding.errorLayout.visibility = View.VISIBLE
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
        lifecycleScope.launch {
            productsViewModel.isRefreshing.collect {
                binding.swipeRefreshLayout.isRefreshing = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}