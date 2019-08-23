package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.gameId.GameId

interface GamesIdRepository {
    fun getGamesId(accountId: String, endIndex: Int, startIndex: Int, apiKey: String, listener: GamesIdRepositoryResult)
}

interface GamesIdRepositoryResult {
    fun onDataReady(matchIdList: List<GameId>)
    fun onError(throwable: Throwable)
}