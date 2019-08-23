package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.Api
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepositoryRemote(private val api: Api) :
    MatchRepository {

    override fun getMatchInform(gameId: Long, apiKey: String, listener: MatchInformResult) {
        api.getInformMatch(
            gameId,
            apiKey
        ).enqueue(object : Callback<Match> {
            override fun onResponse(call: Call<Match>, response: Response<Match>) {
                listener.onDataReady(
                    response.body()!!.teams,
                    response.body()!!.participants
                )
            }

            override fun onFailure(call: Call<Match>, throwable: Throwable) {
                listener.onError(throwable)
            }
        })
    }
}