package com.example.footballapiapp.models.network

import com.example.footballapiapp.models.ui.StatisticsUI
import com.google.gson.annotations.SerializedName

data class StatisticsNM(
    @SerializedName("leagueId") val leagueId: String,
    @SerializedName("teamId") val teamId: String,
    @SerializedName("season") val season: String,
    @SerializedName("leagueName") val leagueName: String,
    @SerializedName("leagueLogo") val leagueLogo: String,
    @SerializedName("played") val played: String,
    @SerializedName("wins") val wins: String,
    @SerializedName("draws") val draws: String,
    @SerializedName("loses") val looses: String,
    @SerializedName("form") val form: String,
    @SerializedName("goalsForTotal") val goalsForTotal: String,
    @SerializedName("goalsForAverage") val goalsForAverage: String,
    @SerializedName("goalsAgainstTotal") val goalsAgainstTotal: String,
    @SerializedName("goalsAgainstAverage") val goalsAgainstAverage: String

)

fun StatisticsNM.toStatisticsUI(): StatisticsUI {

    val played = this.played.split(",").toList()
    val wins = this.wins.split(",").toList()
    val draws = this.draws.split(",").toList()
    val looses = this.looses.split(",").toList()

    val goalsForTotal = this.goalsForTotal.split(",").toList()
    val goalsForAverage = this.goalsForAverage.split(",").toList()
    val goalsAgainstTotal = this.goalsAgainstTotal.split(",").toList()
    val goalsAgainstAverage = this.goalsAgainstAverage.split(",").toList()

    val playedHome = played[0]
    val playedAway = played[1]
    val playedTotal = played[2]

    val winsHome = wins[0]
    val winsAway = wins[1]
    val winsTotal = wins[2]

    val drawsHome = draws[0]
    val drawsAway = draws[1]
    val drawsTotal = draws[2]

    val loosesHome = looses[0]
    val loosesAway = looses[1]
    val loosesTotal = looses[2]

    val goalsForTotalHome = goalsForTotal[0]
    val goalsForTotalAway = goalsForTotal[1]
    val goalsForTotalTotal = goalsForTotal[2]

    val goalsForAverageHome = goalsForAverage[0]
    val goalsForAverageAway = goalsForAverage[1]
    val goalsForAverageTotal = goalsForAverage[2]

    val goalsAgainstTotalHome = goalsAgainstTotal[0]
    val goalsAgainstTotalAway = goalsAgainstTotal[1]
    val goalsAgainstTotalTotal = goalsAgainstTotal[2]

    val goalsAgainstAverageHome = goalsAgainstAverage[0]
    val goalsAgainstAverageAway = goalsAgainstAverage[1]
    val goalsAgainstAverageTotal = goalsAgainstAverage[2]

    return StatisticsUI(
        playedHome,
        playedAway,
        playedTotal,
        winsHome,
        winsAway,
        winsTotal,
        drawsHome,
        drawsAway,
        drawsTotal,
        loosesHome,
        loosesAway,
        loosesTotal,
        goalsForTotalHome,
        goalsForTotalAway,
        goalsForTotalTotal,
        goalsForAverageHome,
        goalsForAverageAway,
        goalsForAverageTotal,
        goalsAgainstTotalHome,
        goalsAgainstTotalAway,
        goalsAgainstTotalTotal,
        goalsAgainstAverageHome,
        goalsAgainstAverageAway,
        goalsAgainstAverageTotal,
        this.leagueName,
        this.leagueLogo,
        this.form
    )
}