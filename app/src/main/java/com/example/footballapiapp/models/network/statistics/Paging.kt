package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName


data class Paging(

    @SerializedName("current") val current: Int,
    @SerializedName("total") val total: Int
)