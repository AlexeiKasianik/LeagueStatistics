package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class ParticipantIdentitie(

    @SerializedName("player")
    val player: Player,

    @SerializedName("participantId")
    val participantId: Int
)