package com.example.macttestapp.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.example.macttestapp.MactTestApp
import com.example.macttestapp.R
import com.example.macttestapp.databinding.FragmentSettingsBinding
import com.example.macttestapp.ui.state.SettingsScreenState
import com.example.macttestapp.ui.viewmodel.SettingsViewModel
import com.example.macttestapp.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding ?: throw RuntimeException("FragmentSettingsBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val settingsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]
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
            val serverAddress = binding.etServerAddress.text.toString().trim()
            if (URLUtil.isValidUrl(serverAddress) && !serverAddress.contains(' ')) {
                binding.tilServerAddress.isErrorEnabled = false
                settingsViewModel.updateServerAddress(serverAddress, requireActivity().application)
                binding.retryLayout.visibility = View.VISIBLE
            } else {
                binding.tilServerAddress.error = getString(R.string.wrong_address_format)
            }
        }
        binding.btNo.setOnClickListener {
            binding.retryLayout.visibility = View.GONE
        }
        binding.btYes.setOnClickListener {
            restartApp()
        }
    }

    private fun restartApp() {
        val packageManager = requireActivity().application.packageManager
        val intent =
            packageManager.getLaunchIntentForPackage(requireActivity().application.packageName)
        val componentName = intent!!.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        requireActivity().application.startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            settingsViewModel.settingsScreenState.collect { state ->
                when (state) {
                    is SettingsScreenState.Success -> {
                        binding.tvServerStatus.text = getString(R.string.server_working)
                    }

                    is SettingsScreenState.Loading -> {
                        binding.tvServerStatus.text = getString(R.string.server_loading)
                    }

                    is SettingsScreenState.Error -> {
                        binding.tvServerStatus.text = getString(R.string.server_error)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}