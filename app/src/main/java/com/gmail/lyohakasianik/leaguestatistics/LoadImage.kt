package com.gmail.lyohakasianik.leaguestatistics

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadRoundImage( context: Context,url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(imageView)
}