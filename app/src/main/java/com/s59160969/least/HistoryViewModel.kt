package com.s59160969.least

import android.app.Application
import android.view.animation.Transformation
import com.s59160969.least.database.LeastDatabaseDAO
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.s59160969.least.database.LeastScore
import kotlinx.coroutines.*
import kotlin.math.log

class HistoryViewModel (val database: LeastDatabaseDAO, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val scores = database.getScoreBoard()

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent : LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar(){
        _showSnackbarEvent.value = null
    }

    fun onClear(){
        uiScope.launch {
            clearHistory()
            _showSnackbarEvent.value =  true
        }
    }

    private suspend fun clearHistory() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
