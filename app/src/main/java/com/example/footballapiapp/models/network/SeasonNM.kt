package com.example.footballapiapp.models.network

import com.google.gson.annotations.SerializedName

data class SeasonNM(
    @SerializedName("0") val year2008: Int,
    @SerializedName("1") val year2009: Int,
    @SerializedName("2") val year2010: Int,
    @SerializedName("3") val year2011: Int,
    @SerializedName("4") val year2012: Int,
    @SerializedName("5") val year2013: Int,
    @SerializedName("6") val year2014: Int,
    @SerializedName("7") val year2015: Int,
    @SerializedName("8") val year2016: Int,
    @SerializedName("9") val year2017: Int,
    @SerializedName("10") val year2018: Int,
    @SerializedName("11") val year2019: Int,
    @SerializedName("12") val year2020: Int,
    @SerializedName("13") val year2021: Int,
    @SerializedName("14") val year2022: Int,
    @SerializedName("15") val year2023: Int
)

fun SeasonNM.toList(): List<String> {
    return listOf(
        year2008.toString(),
        year2009.toString(),
        year2010.toString(),
        year2011.toString(),
        year2012.toString(),
        year2013.toString(),
        year2014.toString(),
        year2015.toString(),
        year2016.toString(),
        year2017.toString(),
        year2018.toString(),
        year2019.toString(),
        year2020.toString(),
        year2021.toString(),
        year2022.toString(),
        year2023.toString()
    )
}
