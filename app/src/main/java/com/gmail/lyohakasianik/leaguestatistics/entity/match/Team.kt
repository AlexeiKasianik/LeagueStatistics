package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Team(

    @SerializedName("win")
    var win: String = "",

    @SerializedName("teamId")
    var teamId: Int = 0

) : RealmObject()
