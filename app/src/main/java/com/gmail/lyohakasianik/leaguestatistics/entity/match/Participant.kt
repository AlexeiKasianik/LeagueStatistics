package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Participant(

    @SerializedName("spell1Id")
    var spell1Id: Int,

    @SerializedName("spell2Id")
    var spell2Id: Int,

    @SerializedName("participantId")
    var participantId: Int,

    @SerializedName("teamId")
    var teamId: Int,

    @SerializedName("championId")
    var championId: Int,


    @SerializedName("stats")
    var stats: Stats
)