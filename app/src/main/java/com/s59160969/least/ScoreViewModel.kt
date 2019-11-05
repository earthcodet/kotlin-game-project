package com.s59160969.least

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) :ViewModel() {
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    private val _eventHome = MutableLiveData<Boolean>()
    val eventHome: LiveData<Boolean>
        get() = _eventHome

    private val _eventPost = MutableLiveData<Boolean>()
    val eventPost: LiveData<Boolean>
        get() = _eventPost

    init {
        _score.value = finalScore
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }

    //play Agin
    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }
    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }

    //Home
    fun onHome() {
        _eventHome.value = true
    }
    fun onHomeinComplete() {
        _eventHome.value = false
    }

    //post
    fun onPost() {
        _eventPost.value = true
    }
    fun onPostinComplete() {
        _eventPost.value = false
    }
}