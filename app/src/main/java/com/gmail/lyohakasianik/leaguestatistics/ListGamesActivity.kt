package com.gmail.lyohakasianik.leaguestatistics

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.lyohakasianik.leaguestatistics.customView.CircleDiagram
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.mvvm.LeagueStatisticsViewModel
import com.gmail.lyohakasianik.leaguestatistics.mvvm.MVVMState
import kotlinx.android.synthetic.main.layout_list_games.*

class ListGamesActivity : FragmentActivity(), ListGamesAdapter.onClickListener {

    private lateinit var adapterForRecyclerView: ListGamesAdapter
    private lateinit var viewModel: LeagueStatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_list_games)

        circleDiagram.findViewById<CircleDiagram>(R.id.circleDiagram)
        circleDiagram.arrayNumbers ="0 1".split(" ").map { it.toIntOrNull() }.filterNotNull()

        val nameSummoner = intent.getStringExtra("nameSummoner")

        viewModel = ViewModelProviders.of(this).get(LeagueStatisticsViewModel::class.java)
        viewModel.loadMatchList(nameSummoner.toString(), API_KEY)



        viewModel.state.observe(this, Observer {
            when (it) {
                is MVVMState.Data -> {
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.isNestedScrollingEnabled = false
                    adapterForRecyclerView = ListGamesAdapter(it.listMatch, this)
                    recyclerView.adapter = adapterForRecyclerView
                    Log.e("ZXCV",viewModel.getWinAndLose())
                    circleDiagram.arrayNumbers = viewModel.getWinAndLose().split(" ").map { it.toIntOrNull() }.filterNotNull()

                }
            }
        })

        nameSummonerTextView.text = nameSummoner

        buttonBackListGames.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onItemClick(item: Match) {
        val intent = Intent(this, GameStatisticsActivity::class.java)
        intent.putExtra("id", item.idMatch.toString() + "," + item.nameSummoner)
        startActivity(intent)
    }
}