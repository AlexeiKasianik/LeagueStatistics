package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.gameId.AllMatchesId
import io.reactivex.Single

interface GamesIdRepository {
    fun getGamesId(accountId: String, endIndex: Int, startIndex: Int, apiKey: String): Single<AllMatchesId>
}
