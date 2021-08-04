package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName


data class Biggest (

    @SerializedName("streak") val streak : Streak,
    @SerializedName("wins") val wins : Wins,
    @SerializedName("loses") val loses : Loses,
    @SerializedName("goals") val goals : Goals
)

data class Streak (

	@SerializedName("wins") val wins : Int,
	@SerializedName("draws") val draws : Int,
	@SerializedName("loses") val loses : Int
)