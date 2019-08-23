package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Stats(

    @SerializedName("goldEarned")
    val goldEarned: Int,

    @SerializedName("totalDamageDealtToChampions")
    val totalDamageDealtToChampions: Long,

    @SerializedName("champLevel")
    val champLevel: Int,

    @SerializedName("visionScore")
    val visionScore: Long,

    @SerializedName("kills")
    val kills: Int,

    @SerializedName("deaths")
    val deaths: Int,

    @SerializedName("assists")
    val assists: Int,

    @SerializedName("win")
    val win: Boolean,

    @SerializedName("neutralMinionsKilled")
    val neutralMinionsKilled: Int,

    @SerializedName("totalMinionsKilled")
    val totalMinionsKilled: Int
    )