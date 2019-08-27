package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName

data class Match(

    val idSummonerInGame: Int,

    val nameSummoner: String,

    val idMatch: Long,

    @SerializedName("participantIdentities")
    val participantIdentities: List<ParticipantIdentitie>,

    @SerializedName("teams")
    val teams: List<Team>?,

    @SerializedName("participants")
    val participants: List<Participant>?,

    @SerializedName("gameDuration")
    val gameDuration: Long,

    @SerializedName("gameCreation")
    val gameCreation: Long
)