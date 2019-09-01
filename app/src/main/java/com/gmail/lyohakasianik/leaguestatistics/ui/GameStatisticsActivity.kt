package com.gmail.lyohakasianik.leaguestatistics.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmail.lyohakasianik.leaguestatistics.API_KEY
import com.gmail.lyohakasianik.leaguestatistics.R
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.mvvm.LeagueStatisticsViewModel
import com.gmail.lyohakasianik.leaguestatistics.mvvm.MVVMState

import kotlinx.android.synthetic.main.layout_game_statistics.*

class GameStatisticsActivity : FragmentActivity() {

    private lateinit var viewModel: LeagueStatisticsViewModel
    private lateinit var listImageView: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_game_statistics)

        viewModel = ViewModelProviders.of(this).get(LeagueStatisticsViewModel::class.java)

        val idAndName: List<String> = intent.getStringExtra("id").split(",")

        val summonerName = idAndName[1]
        val idGame = idAndName[0]

        viewModel.loadMatchInform(summonerName, idGame.toLong(), API_KEY)
        viewModel.state.observe(this, Observer {
            when (it) {
                is MVVMState.Data -> {
                    getInform(it.listMatch[0])
                }
            }
        })

        buttonBackStatisticsActivity.setOnClickListener {
            onBackPressed()
        }

        buttonAddGameInStatistic.setOnClickListener {
            viewModel.addGameInDb()
            Toast.makeText(this, "Игра была добавлена в список статистики", Toast.LENGTH_LONG).show()
        }
    }


    private fun getInform(match: Match) {
        textViewStatisticGameScore.text = viewModel.returnKDASlash(match)

        textViewStatisticGameKdaNumber.text = viewModel.returnKDA(match)

        textViewStatisticGameTimeNumber.text = viewModel.getTime(match.gameDuration)

        textViewStatisticGameKdaOne.text = viewModel.getMinions(
            match.participants[0]!!.stats!!.totalMinionsKilled,
            match.participants[0]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaTwo.text = viewModel.getMinions(
            match.participants[1]!!.stats!!.totalMinionsKilled,
            match.participants[1]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaThree.text = viewModel.getMinions(
            match.participants[2]!!.stats!!.totalMinionsKilled,
            match.participants[2]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaFour.text = viewModel.getMinions(
            match.participants[3]!!.stats!!.totalMinionsKilled,
            match.participants[3]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaFife.text = viewModel.getMinions(
            match.participants[4]!!.stats!!.totalMinionsKilled,
            match.participants[4]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaSix.text = viewModel.getMinions(
            match.participants[5]!!.stats!!.totalMinionsKilled,
            match.participants[5]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaSeven.text = viewModel.getMinions(
            match.participants[6]!!.stats!!.totalMinionsKilled,
            match.participants[6]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaEight.text = viewModel.getMinions(
            match.participants[7]!!.stats!!.totalMinionsKilled,
            match.participants[7]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaNine.text = viewModel.getMinions(
            match.participants[8]!!.stats!!.totalMinionsKilled,
            match.participants[8]!!.stats!!.neutralMinionsKilled
        ).toString()

        textViewStatisticGameKdaTen.text = viewModel.getMinions(
            match.participants[9]!!.stats!!.totalMinionsKilled,
            match.participants[9]!!.stats!!.neutralMinionsKilled
        ).toString()

        listImageView = listOf(
            imageViewStatisticGameSix,
            imageViewStatisticGameSeven,
            imageViewStatisticGameEight,
            imageViewStatisticGameNine,
            imageViewStatisticGameTen,
            imageViewStatisticGameEleven,
            imageViewStatisticGameTwelve,
            imageViewStatisticGameThirteen,
            imageViewStatisticGameFourteen,
            imageViewStatisticGameFifteen
        )

        viewModel.loadImage(listImageView, match, this)
        viewModel.loadimageSummoner(imageViewStatisticGameOne, match,this)

        textViewStatisticGameGoldOne.text = match.participants[0]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldTwo.text = match.participants[1]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldThree.text = match.participants[2]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldFour.text = match.participants[3]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldFife.text = match.participants[4]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldSix.text = match.participants[5]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldSeven.text = match.participants[6]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldEight.text = match.participants[7]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldNine.text = match.participants[8]!!.stats!!.goldEarned.toString()
        textViewStatisticGameGoldTen.text = match.participants[9]!!.stats!!.goldEarned.toString()

        textViewStatisticGameDamageOne.text = match.participants[0]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageTwo.text = match.participants[1]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageThree.text = match.participants[2]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageFour.text = match.participants[3]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageFife.text = match.participants[4]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageSix.text = match.participants[5]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageSeven.text = match.participants[6]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageEight.text = match.participants[7]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageNine.text = match.participants[8]!!.stats!!.totalDamageDealtToChampions.toString()
        textViewStatisticGameDamageTen.text = match.participants[9]!!.stats!!.totalDamageDealtToChampions.toString()

        textViewStatisticGameLvlOne.text = match.participants[0]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlTwo.text = match.participants[1]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlThree.text = match.participants[2]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlFour.text = match.participants[3]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlFife.text = match.participants[4]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlSix.text = match.participants[5]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlSeven.text = match.participants[6]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlEight.text = match.participants[7]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlNine.text = match.participants[8]!!.stats!!.champLevel.toString()
        textViewStatisticGameLvlTen.text = match.participants[9]!!.stats!!.champLevel.toString()

        textViewStatisticGameVisibilityOne.text = match.participants[0]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityTwo.text = match.participants[1]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityThree.text = match.participants[2]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityFour.text = match.participants[3]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityFife.text = match.participants[4]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilitySix.text = match.participants[5]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilitySeven.text = match.participants[6]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityEight.text = match.participants[7]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityNine.text = match.participants[8]!!.stats!!.visionScore.toString()
        textViewStatisticGameVisibilityTen.text = match.participants[9]!!.stats!!.visionScore.toString()

        textViewStatisticGameKillsOne.text = match.participants[0]!!.stats!!.kills.toString()
        textViewStatisticGameKillsTwo.text = match.participants[1]!!.stats!!.kills.toString()
        textViewStatisticGameKillsThree.text = match.participants[2]!!.stats!!.kills.toString()
        textViewStatisticGameKillsFour.text = match.participants[3]!!.stats!!.kills.toString()
        textViewStatisticGameKillsFife.text = match.participants[4]!!.stats!!.kills.toString()
        textViewStatisticGameKillsSix.text = match.participants[5]!!.stats!!.kills.toString()
        textViewStatisticGameKillsSeven.text = match.participants[6]!!.stats!!.kills.toString()
        textViewStatisticGameKillsEight.text = match.participants[7]!!.stats!!.kills.toString()
        textViewStatisticGameKillsNine.text = match.participants[8]!!.stats!!.kills.toString()
        textViewStatisticGameKillsTen.text = match.participants[9]!!.stats!!.kills.toString()

        textViewStatisticGameDeathOne.text = match.participants[0]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathTwo.text = match.participants[1]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathThree.text = match.participants[2]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathFour.text = match.participants[3]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathFife.text = match.participants[4]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathSix.text = match.participants[5]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathSeven.text = match.participants[6]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathEight.text = match.participants[7]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathNine.text = match.participants[8]!!.stats!!.deaths.toString()
        textViewStatisticGameDeathTen.text = match.participants[9]!!.stats!!.deaths.toString()

        textViewStatisticGameAssistsOne.text = match.participants[0]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsTwo.text = match.participants[1]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsThree.text = match.participants[2]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsFour.text = match.participants[3]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsFife.text = match.participants[4]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsSix.text = match.participants[5]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsSeven.text = match.participants[6]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsEight.text = match.participants[7]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsNine.text = match.participants[8]!!.stats!!.assists.toString()
        textViewStatisticGameAssistsTen.text = match.participants[9]!!.stats!!.assists.toString()
    }
}