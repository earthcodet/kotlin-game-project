package com.s59160969.least

import android.app.Application
import android.view.animation.Transformation
import com.s59160969.least.database.LeastDatabaseDAO
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.s59160969.least.database.LeastScore
import kotlinx.coroutines.*
import kotlin.math.log

class HistoryViewModel (val database: LeastDatabaseDAO, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val scores = database.getAllScore()
    val scoreString = Transformations.map(scores){scores ->
        formatDisplay(scores, application.resources)
    }
    private var toScore = MutableLiveData<LeastScore?>()

    init {
        initializeTonight()
    }

    private fun initializeTonight() {
       uiScope.launch {
           toScore.value = getToScoreFromDatabase()
       }
    }

    private suspend fun getToScoreFromDatabase(): LeastScore? {
        return withContext(Dispatchers.IO) {
            var least = database.getToScore()
            least
        }
    }
    fun onTestData(){
        uiScope.launch {
            val newLeastScore = LeastScore()
            insert(newLeastScore)
        }
    }

    private suspend fun insert(score: LeastScore) {
        withContext(Dispatchers.IO){
            database.insert(score)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
