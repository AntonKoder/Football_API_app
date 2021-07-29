package com.example.footballapiapp.repository.network

import com.example.footballapiapp.models.network.CountryNM
import com.example.footballapiapp.models.network.TeamNM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("Football/countries.php")
    fun getCountries(): Call<List<CountryNM>>

    @GET("Football/teams.php")
    fun getTeams(
        @Query("country") country: String
    ): Call<List<TeamNM>>

}