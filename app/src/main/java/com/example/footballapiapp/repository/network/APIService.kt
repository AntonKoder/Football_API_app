package com.example.footballapiapp.repository.network

import com.example.footballapiapp.models.network.CountryNM
import com.example.footballapiapp.models.network.SeasonNM
import com.example.footballapiapp.models.network.TeamNM
import com.example.footballapiapp.models.network.league.LeagueNM
import com.example.footballapiapp.models.network.statistics.StatisticsNM
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
        @Query("season") season: String,
        @Query("country") country: String,
        @Query("team_id") teamId: String
    ): Call<LeagueNM>

    @GET("statistics.php")
    fun getStatisticsByTeam(
        @Query("team_id") teamId: String,
        @Query("season") season: String,
        @Query("league_id") leagueId: String
    ): Call<StatisticsNM>

}