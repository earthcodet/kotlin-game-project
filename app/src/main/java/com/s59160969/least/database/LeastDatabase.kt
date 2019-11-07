package com.s59160969.least.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LeastScore::class],version = 1,exportSchema = false)
abstract class LeastDatabase : RoomDatabase() {
    abstract val leastDatabaseDAO: LeastDatabaseDAO

    companion object{
        @Volatile
        private var INSTANCE: LeastDatabase? = null

        fun getInstance(context: Context):LeastDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LeastDatabase::class.java,
                        "data_least_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
