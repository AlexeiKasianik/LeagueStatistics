package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.entity.gameId.AllMatchesId
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.entity.summoner.Summoner
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("lol/summoner/v4/summoners/by-name/{name}")
    fun getIdSummoner(
        @Path("name") name: String,
        @Query("api_key") apiKey: String
    ): Single<Summoner>


    @GET("lol/match/v4/matchlists/by-account/{accountId}")
    fun getListGamesId(
        @Path("accountId") accountId: String,
        @Query("endIndex") endIndex: Int,
        @Query("beginIndex") beginIndex: Int,
        @Query("api_key") apiKey: String
    ): Single<AllMatchesId>


    @GET("lol/match/v4/matches/{gameId}")
    fun getInformMatch(
        @Path("gameId") gameId: Long,
        @Query("api_key") apiKey: String
    ): Single<Match>
}
