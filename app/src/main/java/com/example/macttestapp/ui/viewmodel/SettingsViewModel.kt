package com.example.macttestapp.ui.viewmodel

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.example.macttestapp.MactTestApp
import com.example.macttestapp.data.Mapper
import com.example.macttestapp.data.RepositoryImpl
import com.example.macttestapp.data.network.ApiFactory
import com.example.macttestapp.domain.usecases.GetServerStatusUseCase
import com.example.macttestapp.ui.state.SettingsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SettingsViewModel(
    application: Application,
) : AndroidViewModel(application) {

    val mapper = Mapper()
    val apiService = ApiFactory(application).apiService
    val repo = RepositoryImpl(apiService, mapper)
    private val getServerStatusUseCase: GetServerStatusUseCase = GetServerStatusUseCase(repo)


    private val _settingsScreenState =
        MutableStateFlow<SettingsScreenState>(SettingsScreenState.Loading)
    val settingsScreenState = _settingsScreenState.asStateFlow()

    init {
        getServerStatus()
    }

    fun getServerStatus() {
        viewModelScope.launch {
            getServerStatusUseCase()
                .onStart { _settingsScreenState.emit(SettingsScreenState.Loading) }
                .catch {
                    _settingsScreenState.emit(SettingsScreenState.Error)
                }.collect { status ->
                    if (status.code == SUCCESS_CODE)
                        _settingsScreenState.emit(SettingsScreenState.Success)
                    else
                        _settingsScreenState.emit(SettingsScreenState.Error)
                }
        }
    }

    fun updateServerAddress(serverAddress: String, application: Application) {
        val preferManager = PreferenceManager.getDefaultSharedPreferences(application)
        preferManager.edit().putString(MactTestApp.BASE_URL, serverAddress).apply()
    }

    companion object {

        private const val SUCCESS_CODE = "200"
    }
}