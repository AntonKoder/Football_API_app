package com.example.footballapiapp.models.network

import com.google.gson.annotations.SerializedName

data class RegDepNM(
    @SerializedName("deposit") val deposit: Int,
    @SerializedName("registration") val registration: Int
)
