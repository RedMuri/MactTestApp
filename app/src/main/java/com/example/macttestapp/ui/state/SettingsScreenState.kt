package com.example.macttestapp.ui.state

sealed class SettingsScreenState {
    object Loading : SettingsScreenState()
    object Success : SettingsScreenState()
    object Error : SettingsScreenState()
}