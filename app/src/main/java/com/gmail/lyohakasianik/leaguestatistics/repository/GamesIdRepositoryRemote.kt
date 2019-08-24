package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.Api
import com.gmail.lyohakasianik.leaguestatistics.entity.gameId.AllMatchesId
import io.reactivex.Single

class GamesIdRepositoryRemote(private val api: Api) :
    GamesIdRepository {

    override fun getGamesId(
        accountId: String,
        endIndex: Int,
        startIndex: Int,
        apiKey: String
    ): Single<AllMatchesId> {
        return api.getListGamesId(
            accountId,
            endIndex,
            startIndex,
            apiKey
        )
    }
}