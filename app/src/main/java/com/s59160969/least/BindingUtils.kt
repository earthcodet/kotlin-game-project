package com.s59160969.least

import android.annotation.SuppressLint
import android.graphics.Color
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

@BindingAdapter("firstScoreImage")
fun ImageView.setFirstScoreImage(item: LeastScore) {
   when (getIndexScoreboard()) {
        1 -> setImageResource(R.drawable.medal)
        else -> visibility = View.GONE
    }
}
@SuppressLint("ResourceAsColor")
@BindingAdapter("topBackgroundScore")
fun View.setTopBackgroundScore(item: LeastScore) {
    when (getIndexScoreboard()) {
        1 ->  setBackgroundColor(Color.parseColor("#e3b10b"))
        2 ->  setBackgroundColor(Color.parseColor("#cacdd4"))
        3 ->  setBackgroundColor(Color.parseColor("#d6802e"))
        else -> setBackgroundColor(Color.parseColor("#FFFFFF"))
    }
}

