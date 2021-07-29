package com.example.footballapiapp.repository.network

import com.example.footballapiapp.models.network.CountryNM


interface NetworkRepository {

    suspend fun getCountries(): List<CountryNM>

}