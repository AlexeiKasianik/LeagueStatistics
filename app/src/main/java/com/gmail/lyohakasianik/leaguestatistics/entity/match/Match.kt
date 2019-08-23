package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("teams")
    var teams: List<Team>,

    @SerializedName("participants")
    var participants: List<Participant>


)