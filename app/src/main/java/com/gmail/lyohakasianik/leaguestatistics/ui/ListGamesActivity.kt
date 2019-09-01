package com.gmail.lyohakasianik.leaguestatistics.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.lyohakasianik.leaguestatistics.databaseIcon.API_KEY
import com.gmail.lyohakasianik.leaguestatistics.R
import com.gmail.lyohakasianik.leaguestatistics.customView.CircleDiagram
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.mvvm.LeagueStatisticsViewModel
import com.gmail.lyohakasianik.leaguestatistics.mvvm.MVVMState
import kotlinx.android.synthetic.main.layout_list_games.*

class ListGamesActivity : FragmentActivity(),
    ListGamesAdapter.onClickListener {

    private lateinit var adapterForRecyclerView: ListGamesAdapter
    private lateinit var viewModel: LeagueStatisticsViewModel
    private var nameSummoner: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_list_games)

        viewModel = ViewModelProviders.of(this).get(LeagueStatisticsViewModel::class.java)
        nameSummoner = intent.getStringExtra("nameSummoner")

        if (nameSummoner == "start database") {
            viewModel.getMatchesDb()
        } else {
            viewModel.loadMatchList(nameSummoner, API_KEY)
        }

        circleDiagram.findViewById<CircleDiagram>(R.id.circleDiagram)
        circleDiagram.arrayNumbers = "0 1".split(" ").map { it.toIntOrNull() }.filterNotNull()

        viewModel.state.observe(this, Observer {
            when (it) {
                is MVVMState.Data -> {
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.isNestedScrollingEnabled = false
                    adapterForRecyclerView =
                        ListGamesAdapter(it.listMatch, this)
                    recyclerView.adapter = adapterForRecyclerView
                    Log.e("ZXCV", viewModel.getWinAndLose())
                    circleDiagram.arrayNumbers =
                        viewModel.getWinAndLose().split(" ").map { it.toIntOrNull() }.filterNotNull()
                    nameSummonerTextView.text = it.listMatch[0].nameSummoner
                }
            }
        })

        buttonBackListGames.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onItemClick(item: Match) {
        if (nameSummoner == "start database"){
            val intent = Intent(this, GameStatisticsActivityDatabase::class.java)
            intent.putExtra("id", item.idMatch.toString() + "," + item.nameSummoner)
            startActivity(intent)
        }else{
            val intent = Intent(this, GameStatisticsActivity::class.java)
            intent.putExtra("id", item.idMatch.toString() + "," + item.nameSummoner)
            startActivity(intent)
        }
    }
    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show()
    }
}