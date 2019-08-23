package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Team(

    @SerializedName("win")
    val win: String,

    @SerializedName("teamId")
    val teamId: Int

)
