package com.gmail.lyohakasianik.leaguestatistics.loadimage

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun loadRoundImageMini(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(20))
        .into(imageView)
}

fun loadRoundImageMaxi(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(32))
        .into(imageView)
}