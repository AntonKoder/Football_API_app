package com.example.footballapiapp.models.ui

data class LeagueUI(
    val id: String,
    val name: String,
    val logo: String,
    val country: String,
    val seasons: List<String>
)
