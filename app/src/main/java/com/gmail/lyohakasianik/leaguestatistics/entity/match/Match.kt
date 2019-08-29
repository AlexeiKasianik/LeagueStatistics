package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Match(

    var idSummonerInGame: Int = 0,

    var nameSummoner: String = "",

    var idMatch: Long? = 0,

    @SerializedName("participantIdentities")
    var participantIdentities: RealmList<ParticipantIdentitie> = RealmList(),

    @SerializedName("teams")
    var teams: RealmList<Team> = RealmList(),

    @SerializedName("participants")
    var participants: RealmList<Participant> = RealmList(),

    @SerializedName("gameDuration")
    var gameDuration: Long = 0,

    @SerializedName("gameCreation")
    var gameCreation: Long = 0
):RealmObject()