package com.s59160969.least.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_least_table")
data class LeastScore (
    @PrimaryKey(autoGenerate = true)
    var leastId: Long = 0L,
    @ColumnInfo(name = "time_game")
    var date: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "score_game")
    var leastScore: Int = 0
)