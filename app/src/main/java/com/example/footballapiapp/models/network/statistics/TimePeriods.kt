package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName


data class Period_0_15(

    @SerializedName("total")
    val total: Int,

    @SerializedName("percentage")
    val percentage: String
)

data class Period_16_30(

    @SerializedName("total")
    val total: String,

    @SerializedName("percentage")
    val percentage: String
)

data class Period_31_45(

    @SerializedName("total")
    val total: String,

    @SerializedName("percentage")
    val percentage: String
)

data class Period_46_60(

    @SerializedName("total")
    val total: String,

    @SerializedName("percentage")
    val percentage: String
)

data class Period_61_75(

    @SerializedName("total") val total: Int,
    @SerializedName("percentage") val percentage: String
)

data class Period_76_90(

    @SerializedName("total") val total: Int,
    @SerializedName("percentage") val percentage: String
)

data class Period_91_105(

    @SerializedName("total") val total: String,
    @SerializedName("percentage") val percentage: String
)

data class Period_106_120(

    @SerializedName("total") val total: String,
    @SerializedName("percentage") val percentage: String
)