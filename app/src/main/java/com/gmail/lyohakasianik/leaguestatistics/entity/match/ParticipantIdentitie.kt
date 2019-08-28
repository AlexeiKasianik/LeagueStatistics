package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class ParticipantIdentitie(

    @SerializedName("player")
    val player: Player = Player("",0,""),

    @SerializedName("participantId")
    val participantId: Int = 0
)