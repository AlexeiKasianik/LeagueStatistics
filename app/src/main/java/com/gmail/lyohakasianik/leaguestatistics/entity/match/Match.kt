package com.gmail.lyohakasianik.leaguestatistics.entity.match

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val POI_TABLE_NAME = "MatchDb"

data class Match(

    @PrimaryKey
    var idMatch: Long,

    @SerializedName("teams")
    var teams: List<Team>?,

    @SerializedName("participants")
    var participants: List<Participant>?


)