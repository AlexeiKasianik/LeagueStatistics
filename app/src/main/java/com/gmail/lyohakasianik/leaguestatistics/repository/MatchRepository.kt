package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Participant
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Team
import io.reactivex.Single

interface MatchRepository {
    fun getMatchInform(gameId: Long, apiKey: String): Single<Match>
}