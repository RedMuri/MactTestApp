package com.example.macttestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.macttestapp.databinding.FragmentProductsBinding
import com.example.macttestapp.domain.model.Product
import com.example.macttestapp.ui.adapters.ProductsAdapter
import com.squareup.picasso.Picasso

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentProductsBinding == null")

    private val adapterProducts by lazy {
        ProductsAdapter()
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
    }

    private fun setupRecyclerView() {
        binding.rvItems.adapter = adapterProducts
        val testProducts = listOf(
            Product(
                1,
                "«Один»",
                1000,
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg",
                "some description text",
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg"
            ),
            Product(
                2,
                "«Два»",
                1000,
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg",
                "some description text",
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg"
            ),
            Product(
                3,
                "«Три»",
                1000,
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg",
                "some description text",
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg"
            ),
            Product(
                4,
                "«Четыре»",
                1000,
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg",
                "some description text",
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg"
            ),
            Product(
                5,
                "«Пять»",
                1000,
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg",
                "some description text",
                "https://static.rustore.ru/apk/256380351/content/ICON/d4db7e5a-8a8d-46ad-9509-1cefcf2e40ab.jpg"
            ),
        )
        adapterProducts.submitList(testProducts)
        adapterProducts.onProductClickListener = { product ->
            binding.llItemDetails.visibility = View.VISIBLE
            binding.tvItemDescription.text = product.description
            Picasso.get().load(product.image).into(binding.ivItemImage)
            Picasso.get().load(product.image).into(binding.ivItemImage)
        }
        binding.llItemDetails.setOnClickListener {
            it.visibility = View.GONE
        }
    }
}