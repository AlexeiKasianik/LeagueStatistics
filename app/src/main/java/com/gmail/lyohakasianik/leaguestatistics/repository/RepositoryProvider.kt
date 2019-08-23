package com.gmail.lyohakasianik.leaguestatistics.repository

import com.gmail.lyohakasianik.leaguestatistics.BASE_URL

fun provideSummonerRepository(): SummonerRepository {
    return SummonerRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}

fun provideGamesIdRepository(): GamesIdRepository {
    return GamesIdRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}

fun provideMatchRepository(): MatchRepository {
    return MatchRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}