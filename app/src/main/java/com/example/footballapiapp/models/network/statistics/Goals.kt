package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName


data class Goals(

    @SerializedName("for") val _for: For,
    @SerializedName("against") val against: Against
)

data class For(
    @SerializedName("total") val total: Total,
    @SerializedName("average") val average: Average,
    @SerializedName("minute") val minute: Minute,
    )

data class Against(
    @SerializedName("total") val total: Total,
    @SerializedName("average") val average: Average,
    @SerializedName("minute") val minute: Minute,
)


data class Total(

    @SerializedName("home") val home: Int,
    @SerializedName("away") val away: Int,
    @SerializedName("total") val total: Int
)

data class Average(

    @SerializedName("home") val home: Double,
    @SerializedName("away") val away: Double,
    @SerializedName("total") val total: Double
)

data class Minute(

    @SerializedName("0-15") val period_0_15: Period_0_15,
    @SerializedName("16-30") val period_16_30: Period_16_30,
    @SerializedName("31-45") val periodPeriod_31_45: Period_31_45,
    @SerializedName("46-60") val period_46_60: Period_46_60,
    @SerializedName("61-75") val period_61_75: Period_61_75,
    @SerializedName("76-90") val period_76_90: Period_76_90,
    @SerializedName("91-105") val period_91_105: Period_91_105,
    @SerializedName("106-120") val period_106_120: Period_106_120
)
