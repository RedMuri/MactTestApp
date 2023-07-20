package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ServerStatusDto @Inject constructor(
    @SerializedName("status") val code: String,
)
