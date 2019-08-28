package com.gmail.lyohakasianik.leaguestatistics.entity.match

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.lyohakasianik.leaguestatistics.MATCH_TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = MATCH_TABLE_NAME )
data class Match(

    @PrimaryKey
    val idSummonerInGame: Int? = 0,

    val nameSummoner: String? = "",

    val idMatch: Long? = 0,

    @Embedded
    @SerializedName("participantIdentities")
    val participantIdentities: List<ParticipantIdentitie>? = mutableListOf(),

    @Embedded
    @SerializedName("teams")
    val teams: List<Team>? = mutableListOf(),

    @Embedded
    @SerializedName("participants")
    val participants: List<Participant>? = mutableListOf(),

    @SerializedName("gameDuration")
    val gameDuration: Long? = 0,

    @SerializedName("gameCreation")
    val gameCreation: Long? = 0
)