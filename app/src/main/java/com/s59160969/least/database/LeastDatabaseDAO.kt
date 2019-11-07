package com.s59160969.least.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeastDatabaseDAO {
    @Insert
    fun insert(least: LeastScore)

    @Query("SELECT * FROM data_least_table ORDER BY leastId DESC")
    fun getAllScore(): LiveData<List<LeastScore>>
}
