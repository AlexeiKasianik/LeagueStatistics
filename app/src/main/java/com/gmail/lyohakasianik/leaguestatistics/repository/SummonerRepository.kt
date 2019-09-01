package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.summoner.Summoner
import io.reactivex.Single

interface SummonerRepository {
    fun getSummoner(name: String, apiKey: String): Single<Summoner>
}
