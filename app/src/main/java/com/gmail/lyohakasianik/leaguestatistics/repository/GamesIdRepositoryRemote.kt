package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.Api
import com.gmail.lyohakasianik.leaguestatistics.entity.gameId.AllMatchesId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesIdRepositoryRemote(private val api: Api) :
    GamesIdRepository {

    override fun getGamesId(
        accountId: String,
        endIndex: Int,
        startIndex: Int,
        apiKey: String,
        listener: GamesIdRepositoryResult
    ) {
        api.getListGamesId(
            accountId,
            endIndex,
            startIndex,
            apiKey
        ).enqueue(object : Callback<AllMatchesId> {
            override fun onResponse(call: Call<AllMatchesId>, response: Response<AllMatchesId>) {
                listener.onDataReady(
                    response.body()!!.matchIdList
                )
            }

            override fun onFailure(call: Call<AllMatchesId>, throwable: Throwable) {
                listener.onError(throwable)
            }
        })
    }
}