package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.Api
import com.gmail.lyohakasianik.leaguestatistics.entity.summoner.Summoner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummonerRepositoryRemote(private val api: Api) :
    SummonerRepository {

    override fun getSummoner(name: String, apiKey: String, listener: SummonerResult) {
        api.getIdSummoner(
            name,
            apiKey
        ).enqueue(object : Callback<Summoner> {
            override fun onResponse(call: Call<Summoner>, response: Response<Summoner>) {
                listener.onDataReady(
                    response.body()!!.accountId,
                    response.body()!!.name,
                    response.body()!!.profileIconId,
                    response.body()!!.summonerLevel
                )
            }

            override fun onFailure(call: Call<Summoner>, throwable: Throwable) {
                listener.onError(throwable)
            }
        })
    }
}