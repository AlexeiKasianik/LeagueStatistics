package com.gmail.lyohakasianik.leaguestatistics.entity.match

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.lyohakasianik.leaguestatistics.MATCH_TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = MATCH_TABLE_NAME )
data class Match(

    @PrimaryKey
    val idSummonerInGame: Int?,

    val nameSummoner: String?,

    val idMatch: Long?,

    @Embedded
    @SerializedName("participantIdentities")
    val participantIdentities: List<ParticipantIdentitie>?,

    @Embedded
    @SerializedName("teams")
    val teams: List<Team>?,

    @Embedded
    @SerializedName("participants")
    val participants: List<Participant>?,

    @SerializedName("gameDuration")
    val gameDuration: Long?,

    @SerializedName("gameCreation")
    val gameCreation: Long?
)