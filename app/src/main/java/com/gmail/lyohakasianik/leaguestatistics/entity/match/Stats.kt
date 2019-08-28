package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Stats(

    @SerializedName("goldEarned")
    var goldEarned: Int = 0,

    @SerializedName("totalDamageDealtToChampions")
    var totalDamageDealtToChampions: Long = 0,

    @SerializedName("champLevel")
    var champLevel: Int = 0,

    @SerializedName("visionScore")
    var visionScore: Long = 0,

    @SerializedName("kills")
    var kills: Int = 0,

    @SerializedName("deaths")
    var deaths: Int = 0,

    @SerializedName("assists")
    var assists: Int = 0,

    @SerializedName("win")
    var win: Boolean = true,

    @SerializedName("neutralMinionsKilled")
    var neutralMinionsKilled: Int = 0,

    @SerializedName("totalMinionsKilled")
    var totalMinionsKilled: Int = 0
    )