package com.example.footballapiapp.models.ui

import java.io.Serializable

data class TeamUI(
    val id: String?,
    val name: String?,
    val country: String?,
    val founded: String?,
    val national: String?,
    val logo: String?,
    val venueName: String?,
    val address: String?,
    val city: String?,
    val capacity: String?,
    val surface: String?,
    val image: String?
) : Serializable
