package com.gmail.lyohakasianik.leaguestatistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match

class ListGamesAdapter(private var matches: MutableList<Match>, private val listener: onClickListener) :
    RecyclerView.Adapter<ListGamesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGamesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_game, parent, false)

        val holder = ListGamesHolder(view)

        holder.itemView.setOnClickListener {
            listener.onItemClick(matches[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ListGamesHolder, position: Int) {
        holder.bind(matches[position], matches[position].nameSummoner)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    interface onClickListener {
        fun onItemClick(item: Match)
    }
}