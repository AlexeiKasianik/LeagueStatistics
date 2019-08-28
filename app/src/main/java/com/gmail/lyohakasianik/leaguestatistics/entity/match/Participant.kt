package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Participant(

    @SerializedName("spell1Id")
    var spell1Id: Int = 0,

    @SerializedName("spell2Id")
    var spell2Id: Int = 0,

    @SerializedName("participantId")
    var participantId: Int = 0,

    @SerializedName("teamId")
    var teamId: Int = 0,

    @SerializedName("championId")
    var championId: Int = 0,


    @SerializedName("stats")
    var stats: Stats = Stats(0,0,0,0,0,0,0,true,0,0)
)