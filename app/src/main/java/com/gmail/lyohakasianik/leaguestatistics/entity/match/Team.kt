package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Team(

    @SerializedName("win")
    var win: String = "",

    @SerializedName("teamId")
    var teamId: Int = 0

)
