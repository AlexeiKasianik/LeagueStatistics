package com.gmail.lyohakasianik.leaguestatistics.entity.summoner

import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.google.gson.annotations.SerializedName

data class Summoner(

    @SerializedName("accountId")
    var accountId: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("profileIconId")
    var profileIconId: Int,

    @SerializedName("summonerLevel")
    var summonerLevel: Int
)