package com.example.footballapiapp.models.network.statistics

import com.example.footballapiapp.models.ui.StatisticsUI
import com.google.gson.annotations.SerializedName

data class StatisticsNM(

    @SerializedName("get") val get: String,
    @SerializedName("parameters") val parameters: Parameters,
    @SerializedName("errors") val errors: List<String>,
    @SerializedName("results") val results: Int,
    @SerializedName("paging") val paging: Paging,
    @SerializedName("response") val response: Response
)

fun StatisticsNM.toStatisticsUI(): StatisticsUI {
    return StatisticsUI(
        this.response.fixtures.played.home.toString(),
        this.response.fixtures.played.away.toString(),
        this.response.fixtures.played.total.toString(),
        this.response.fixtures.wins.home,
        this.response.fixtures.wins.away,
        (this.response.fixtures.wins.home.toInt() + this.response.fixtures.wins.away.toInt()).toString(),
        this.response.fixtures.draws.home.toString(),
        this.response.fixtures.draws.away.toString(),
        this.response.fixtures.draws.total.toString(),
        this.response.fixtures.loses.home,
        this.response.fixtures.loses.away,
        (this.response.fixtures.loses.home.toInt() + this.response.fixtures.loses.away.toInt()).toString(),
        this.response.goals._for.total.home.toString(),
        this.response.goals._for.total.away.toString(),
        this.response.goals._for.total.total.toString(),
        this.response.goals._for.average.home.toString(),
        this.response.goals._for.average.away.toString(),
        this.response.goals._for.average.total.toString(),
        this.response.goals.against.total.home.toString(),
        this.response.goals.against.total.away.toString(),
        this.response.goals.against.total.total.toString(),
        this.response.goals.against.average.home.toString(),
        this.response.goals.against.average.away.toString(),
        this.response.goals.against.average.total.toString(),
        this.response.leagueInfo.name,
        this.response.leagueInfo.logo,
        this.response.form
    )
}