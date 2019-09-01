package com.gmail.lyohakasianik.leaguestatistics.entity.match

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Player(

    @SerializedName("summonerName")
    var summonerName: String? = "",

    @SerializedName("profileIcon")
    var profileIcon: Int? = 0,

    @SerializedName("summonerId")
    var summonerId: String? = ""
) : RealmObject()