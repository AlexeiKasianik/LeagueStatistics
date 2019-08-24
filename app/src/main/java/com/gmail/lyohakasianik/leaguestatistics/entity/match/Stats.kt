package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Stats(

    @SerializedName("goldEarned")
    var goldEarned: Int,

    @SerializedName("totalDamageDealtToChampions")
    var totalDamageDealtToChampions: Long,

    @SerializedName("champLevel")
    var champLevel: Int,

    @SerializedName("visionScore")
    var visionScore: Long,

    @SerializedName("kills")
    var kills: Int,

    @SerializedName("deaths")
    var deaths: Int,

    @SerializedName("assists")
    var assists: Int,

    @SerializedName("win")
    var win: Boolean,

    @SerializedName("neutralMinionsKilled")
    var neutralMinionsKilled: Int,

    @SerializedName("totalMinionsKilled")
    var totalMinionsKilled: Int
    )