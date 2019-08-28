package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Player(

    @SerializedName("summonerName")
    val summonerName: String,

    @SerializedName("profileIcon")
    val profileIcon: Int,

    @SerializedName("summonerId")
    val summonerId: String
)