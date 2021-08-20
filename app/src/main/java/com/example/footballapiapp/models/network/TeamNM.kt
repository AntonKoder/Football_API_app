package com.example.footballapiapp.models.network

import com.example.footballapiapp.models.ui.TeamUI
import com.google.gson.annotations.SerializedName

data class TeamNM(

    @SerializedName("team") val team: Team,
    @SerializedName("venue") val venue: Venue
)

fun TeamNM.toTeamUI(): TeamUI {
    return TeamUI(
        this.team.id,
        this.team.name,
        this.team.country,
        this.team.founded,
        this.team.national,
        this.team.logo,
        this.venue.name,
        this.venue.address,
        this.venue.city,
        this.venue.capacity,
        this.venue.surface,
        this.venue.image
    )
}

data class Team(

    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("founded") val founded: String,
    @SerializedName("national") val national: String,
    @SerializedName("logo") val logo: String
)

data class Venue(

    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("capacity") val capacity: String,
    @SerializedName("surface") val surface: String,
    @SerializedName("image") val image: String
)
