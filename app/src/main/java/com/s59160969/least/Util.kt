package com.s59160969.least

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("MM-dd-yyyy','HH:mm")
            .format(systemTime).toString()
}
var indexScoreBoard  = 1

fun getIndexScoreboard()=indexScoreBoard

fun updateIndexScore(){
    indexScoreBoard +=1
}

fun resetIndexScore(){
    indexScoreBoard = 1
}
