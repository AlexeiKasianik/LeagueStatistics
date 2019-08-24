package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.Api
import com.gmail.lyohakasianik.leaguestatistics.entity.summoner.Summoner
import io.reactivex.Single

class SummonerRepositoryRemote(private val api: Api) :
    SummonerRepository {

    override fun getSummoner(name: String, apiKey: String): Single<Summoner> {
        return api.getIdSummoner(
            name,
            apiKey
        )
    }
}