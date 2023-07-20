package com.example.macttestapp.data.model

import com.google.gson.annotations.SerializedName

data class ServerStatusDto(
    @SerializedName("status") val code: String,
)
