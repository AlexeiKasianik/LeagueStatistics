package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Participant(

    @SerializedName("spell1Id")
    val spell1Id: Int,

    @SerializedName("spell2Id")
    val spell2Id: Int,

    @SerializedName("participantId")
    val participantId: Int,

    @SerializedName("teamId")
    val teamId: Int,

    @SerializedName("championId")
    val championId: Int,


    @SerializedName("stats")
    val stats: Stats
)