package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class ParticipantIdentitie(

    @SerializedName("player")
    var player: Player? = Player(),

    @SerializedName("participantId")
    var participantId: Int = 0
):RealmObject()