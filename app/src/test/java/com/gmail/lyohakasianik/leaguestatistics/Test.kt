package com.gmail.lyohakasianik.leaguestatistics

import com.gmail.lyohakasianik.leaguestatistics.repository.Api
import com.gmail.lyohakasianik.leaguestatistics.repository.NetProvider
import com.gmail.lyohakasianik.leaguestatistics.repository.SummonerRepositoryRemote
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class Test {
    var server = MockWebServer()
    private lateinit var api: Api

    @Before
    fun before() {
        server.start()
        val httpUrl = server.url("/")
        val retrofit =
            NetProvider.provideRetrofit(httpUrl.toString(), NetProvider.provideOkHttp(), NetProvider.provideGson())
        api = NetProvider.provideApi(retrofit)
    }

    @After
    fun after() {
        server.shutdown()
    }

    @Test
    fun testSuccessResponseSummonerRepositoryRemote() {
        val repository = SummonerRepositoryRemote(api)
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody(SUCCESS_RESPONSE)
        server.enqueue(mockResponse)

        val summonerName = "RiotSchmick"
        val apiKey = "123456789"

        val testObserver = repository.getSummoner(summonerName, apiKey).test()

        testObserver.assertValue {
            it.name == "RiotSchmick" && it.summonerLevel == 153
        }
    }

    @Test
    fun testFailResponseSummonerRepositoryRemote() {
        val repository = SummonerRepositoryRemote(api)
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(403)
        mockResponse.setBody(FAIL_RESPONSE)
        server.enqueue(mockResponse)

        val summonerName = "RiotSchmick"
        val apiKey = "123456789"

        val testObserver = repository.getSummoner(summonerName, apiKey).test()

        testObserver.assertError {
            it is Exception
        }
    }

}