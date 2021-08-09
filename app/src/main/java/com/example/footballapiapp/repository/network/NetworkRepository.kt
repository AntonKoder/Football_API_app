package com.example.footballapiapp.repository.network

import com.example.footballapiapp.models.network.*


interface NetworkRepository {

    suspend fun getCountries(): List<CountryNM>
    suspend fun getTeams(countryName: String): List<TeamNM>
    suspend fun getSeasons(): SeasonNM
    suspend fun getLeagues(countryName: String, teamId: String): List<LeagueNM>
    suspend fun getTeamStatistics(teamId: String, season: String, leagueId: String): StatisticsNM

}