package com.example.macttestapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.example.macttestapp.MactTestApp
import com.example.macttestapp.databinding.FragmentSettingsBinding
import com.example.macttestapp.ui.state.SettingsScreenState
import com.example.macttestapp.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding ?: throw RuntimeException("FragmentSettingsBinding == null")


    private val settingsViewModel by lazy {
        ViewModelProvider(this)[SettingsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupClickListeners()
        observeServerAddress()
    }

    private fun observeServerAddress() {
        val preferManager =
            PreferenceManager.getDefaultSharedPreferences(requireActivity().application)
        val baseUrl = preferManager.getString(MactTestApp.BASE_URL, null) ?: ""
        binding.etServerAddress.setText(baseUrl)
        binding.etServerAddress.doOnTextChanged { text, _, _, _ ->
            binding.saveButton.isEnabled = text.toString() != baseUrl
        }
    }

    private fun setupClickListeners() {
        binding.checkButton.setOnClickListener {
            settingsViewModel.getServerStatus()
        }
        binding.saveButton.setOnClickListener {
            val serverAddress = binding.etServerAddress.text.toString()
            settingsViewModel.updateServerAddress(serverAddress, requireActivity().application)

        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            settingsViewModel.settingsScreenState.collect { state ->
                when (state) {
                    is SettingsScreenState.Success -> {
                        binding.tvServerStatus.text = "Работает"
                    }

                    is SettingsScreenState.Loading -> {
                        binding.tvServerStatus.text = "Жди"
                    }

                    is SettingsScreenState.Error -> {
                        binding.tvServerStatus.text = "Ошибка"
                    }
                }
            }
        }

    }
}