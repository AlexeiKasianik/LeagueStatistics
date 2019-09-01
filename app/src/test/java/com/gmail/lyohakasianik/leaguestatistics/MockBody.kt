package com.gmail.lyohakasianik.leaguestatistics

val SUCCESS_RESPONSE = """
    {
    "id": "1tL_xNdXfh211DcdH3aDB3W7TXynyF_-HjKPxlFne0Z0",
    "accountId": "R9lpbm8WbCo0cqXPJSodGgF3fvCBa1jqgPFkr8g50N7WVQ",
    "puuid": "Nv0DTaFs4z8SUWabZRTvVrXViY397lh-GYQz204MK-oj6FK8_NkCMJRlKTCMXNBy_9pNKCSpVkOM6w",
    "name": "RiotSchmick",
    "profileIconId": 4213,
    "revisionDate": 1567291435000,
    "summonerLevel": 153
}
""".trimIndent()

val FAIL_RESPONSE = """
    {"status":{"message":"Forbidden","status_code":403}}
""".trimIndent()