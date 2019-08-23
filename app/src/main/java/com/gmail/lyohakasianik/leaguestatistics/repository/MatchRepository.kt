package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.match.Participant
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Team

interface MatchRepository {
    fun getMatchInform(gameId: Long, apiKey: String, listener: MatchInformResult)
}

interface MatchInformResult {
    fun onDataReady(
        teams: List<Team>,
        participants: List<Participant>
    )

    fun onError(throwable: Throwable)
}