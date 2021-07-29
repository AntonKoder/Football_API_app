package com.example.footballapiapp.models.network

import com.example.footballapiapp.models.ui.VenueUI
import com.google.gson.annotations.SerializedName

data class VenueNM(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("capacity")
    val capacity: String,
    @SerializedName("surface")
    val surface: String,
    @SerializedName("image")
    val image: String
)

fun VenueNM.toVenueUI(): VenueUI {
    return VenueUI(
        this.id,
        this.name,
        this.address,
        this.city,
        this.capacity,
        this.surface,
        this.image
    )
}
