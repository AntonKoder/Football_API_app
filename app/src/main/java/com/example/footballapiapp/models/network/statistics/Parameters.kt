package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName


data class Parameters(

    @SerializedName("league") val league: Int,
    @SerializedName("team") val team: Int,
    @SerializedName("season") val season: Int
)