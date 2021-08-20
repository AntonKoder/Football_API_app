package com.example.footballapiapp.repository.network

import com.example.footballapiapp.models.network.CountryNM
import com.example.footballapiapp.models.network.LeagueNM
import com.example.footballapiapp.models.network.SeasonNM
import com.example.footballapiapp.models.network.StatisticsNM
import com.example.footballapiapp.models.network.TeamNM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("countries.php")
    fun getCountries(): Call<List<CountryNM>>

    @GET("teams.php")
    fun getTeams(
        @Query("country") country: String
    ): Call<List<TeamNM>>

    @GET("seasons.php")
    fun getSeasons(): Call<SeasonNM>

    @GET("leagues.php")
    fun getLeague(
        @Query("country") countryName: String,
        @Query("team") teamId: String
    ): Call<List<LeagueNM>>

    @GET("statistics.php")
    fun getStatisticsByTeam(
        @Query("team") teamId: String,
        @Query("season") season: String,
        @Query("league") leagueId: String
    ): Call<StatisticsNM>
}
