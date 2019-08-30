package com.gmail.lyohakasianik.leaguestatistics.entity.gameId

import com.google.gson.annotations.SerializedName

data class AllMatchesId(

    @SerializedName("matches")
    var matchIdList: List<GameId>
)