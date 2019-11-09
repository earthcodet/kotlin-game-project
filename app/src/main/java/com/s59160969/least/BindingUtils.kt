package com.s59160969.least

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.s59160969.least.database.LeastScore

@BindingAdapter("dateScoreString")
fun TextView.setDateScoreString(item: LeastScore) {
    text = convertLongToDateString(item.date)
}


@BindingAdapter("scoreString")
fun TextView.setScoreString(item: LeastScore) {
    text = "${item.leastScore} score"
}

@BindingAdapter("rankingScoreString")
fun TextView.setRankingScoreString(item: LeastScore) {
    text = getIndexScoreboard().toString()
}

@BindingAdapter("scoreImage")
fun ImageView.setScoreImage(item: LeastScore) {
   when (getIndexScoreboard()) {
        1 -> setImageResource(R.drawable.medal)
        else -> visibility = View.GONE
    }
}

