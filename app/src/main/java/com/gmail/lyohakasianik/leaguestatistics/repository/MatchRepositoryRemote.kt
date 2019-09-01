package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import io.reactivex.Single

class MatchRepositoryRemote(private val api: Api) :
    MatchRepository {

    override fun getMatchInform(gameId: Long, apiKey: String): Single<Match> {
        return api.getInformMatch(
            gameId,
            apiKey
        )
    }
}