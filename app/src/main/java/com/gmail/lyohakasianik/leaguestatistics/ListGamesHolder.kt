package com.gmail.lyohakasianik.leaguestatistics

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match

class ListGamesHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val goldTextView = itemView.findViewById<TextView>(R.id.goldTextView)
    private val itemsTextView = itemView.findViewById<TextView>(R.id.itemsTextView)
    private val minionsItemTextView = itemView.findViewById<TextView>(R.id.minionsItemTextView)
    private val damageItemTextView = itemView.findViewById<TextView>(R.id.damageItemTextView)
    private val gameTimerTextView = itemView.findViewById<TextView>(R.id.gameTimerTextView)
    private val itemConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.itemConstraintLayout)


    fun bind(match: Match, summonerName: String) {
        goldTextView.text =
            match.participants!![returnPositionSummoner(match, summonerName)].stats.goldEarned.toString()
        itemsTextView.text =
            match.participants[returnPositionSummoner(match, summonerName)].stats.visionScore.toString()
        minionsItemTextView.text =
            (match.participants[returnPositionSummoner(match, summonerName)].stats.neutralMinionsKilled
                    + match.participants[returnPositionSummoner(
                match,
                summonerName
            )].stats.totalMinionsKilled).toString()
        damageItemTextView.text =
            match.participants[returnPositionSummoner(match, summonerName)].stats.totalDamageDealtToChampions.toString()
        gameTimerTextView.text = getTime(match.gameDuration)

        if (!returnTeam(match, (returnPositionSummoner(match, summonerName))+1)) {
            itemConstraintLayout.setBackgroundResource(
                R.drawable.pink_shape

            )
        }
    }

    fun returnTeam(match: Match, summonerIdGame: Int): Boolean {
        var boolean: Boolean = true
        for (item in match.participants!!) {
            if (item.participantId == summonerIdGame)
                boolean = item.stats.win
        }

        return boolean
    }

    fun returnPositionSummoner(match: Match, summonerName: String): Int {
        var positionSummoner = 0
        for (item in match.participantIdentities) {
            if (item.player.summonerName == summonerName)
                positionSummoner = item.participantId
        }
        return positionSummoner-1
    }

    fun getTime(long: Long): String {
        val min = long / 60
        val sec = long % 60
        return "$min.$sec"
    }
}