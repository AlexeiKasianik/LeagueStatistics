package com.gmail.lyohakasianik.leaguestatistics

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmail.lyohakasianik.leaguestatistics.mvvm.LeagueStatisticsViewModel
import com.gmail.lyohakasianik.leaguestatistics.mvvm.MVVMState
import kotlinx.android.synthetic.main.layout_statistics_selected_games.*

class StatisticsGamesDb:FragmentActivity() {

    private lateinit var viewModel: LeagueStatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_statistics_selected_games)

        viewModel = ViewModelProviders.of(this).get(LeagueStatisticsViewModel::class.java)
        viewModel.getAverages()
        viewModel.state.observe(this, Observer {
            when (it) {
                is MVVMState.DataAverages -> {
                    getInform(it.averages)
                }
            }
        })

        buttonBackStatisticsSelectedChampions.setOnClickListener {
            onBackPressed()
        }


    }

    fun getInform(listAverages: MutableList<Long>){
        textViewGoldNumber.text = listAverages[0].toString()
        textViewDamegeNumber.text = listAverages[1].toString()
        textViewLvlNumber.text = listAverages[2].toString()
        textViewWardsNumber.text = listAverages[3].toString()
        textViewKdaNumber.text = listAverages[4].toString()
        textViewMiddleKillsNumber.text = listAverages[5].toString()
        textViewMiddleDeathNumber.text = listAverages[6].toString()
        textViewMiddleAssistNumber.text = listAverages[7].toString()
    }
}