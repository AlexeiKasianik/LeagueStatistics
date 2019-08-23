package com.gmail.lyohakasianik.leaguestatistics.repository

interface SummonerRepository {
    fun getSummoner(name: String, apiKey: String, listener: SummonerResult)
}

interface SummonerResult {
    fun onDataReady(
        accountId: String,
        name: String,
        profileIconId: Int,
        summonerLevel: Int
    )

    fun onError(throwable: Throwable)

}