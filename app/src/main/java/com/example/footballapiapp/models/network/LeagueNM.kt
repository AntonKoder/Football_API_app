package com.example.footballapiapp.models.network

import com.example.footballapiapp.models.ui.LeagueUI
import com.google.gson.annotations.SerializedName

data class LeagueNM(
    @SerializedName("id") val leagueId: String,
    @SerializedName("name") val leagueName: String,
    @SerializedName("leagueLogo") val logo: String,
    @SerializedName("country") val country: String,
    @SerializedName("availableSeasons") val seasons: String
)

fun LeagueNM.toLeagueUI(): LeagueUI {
    val seasons = this.seasons.split(",").toList()
    return LeagueUI(this.leagueId, this.leagueName, this.logo, this.country, seasons)
}

