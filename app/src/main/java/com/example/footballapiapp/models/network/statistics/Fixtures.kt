package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName


data class Fixtures(

    @SerializedName("played") val played: Played,
    @SerializedName("wins") val wins: Wins,
    @SerializedName("draws") val draws: Draws,
    @SerializedName("loses") val loses: Loses
)

data class Played(

    @SerializedName("home") val home: Int,
    @SerializedName("away") val away: Int,
    @SerializedName("total") val total: Int
)

data class Wins(

    @SerializedName("home") val home: String,
    @SerializedName("away") val away: String
)

data class Draws(

    @SerializedName("home") val home: Int,
    @SerializedName("away") val away: Int,
    @SerializedName("total") val total: Int
)

data class Loses(

    @SerializedName("home") val home: String,
    @SerializedName("away") val away: String
)