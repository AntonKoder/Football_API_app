package com.example.footballapiapp.models.network

import com.example.footballapiapp.models.ui.TeamUI
import com.google.gson.annotations.SerializedName

data class TeamNM(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("founded")
    val founded: String,
    @SerializedName("national")
    val national: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("venue_name")
    val venueName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("capacity")
    val capacity: String,
    @SerializedName("surface")
    val surface: String,
    @SerializedName("image")
    val image: String,
)

fun TeamNM.toTeamUI(): TeamUI {
    return TeamUI(this.id, this.name, this.country, this.founded, this.national, this.logo)
}