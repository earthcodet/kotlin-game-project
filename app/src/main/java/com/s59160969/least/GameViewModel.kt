package com.s59160969.least

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class GameViewModel : ViewModel() {
    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
        get() = _score

    private val _heart = MutableLiveData<Int>()
    val heart:LiveData<Int>
        get() = _heart

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    val _answerList = MutableLiveData<IntArray>()
    val answerList:LiveData<IntArray>
        get() = _answerList

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    var answerGame = answerList.value?.min()
    private val timer: CountDownTimer


    val currentTimeString = Transformations.map(currentTime) { time ->
        "Time : ${DateUtils.formatElapsedTime(time)}"
    }

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L
    }

    init {
        _score.value =  0
        _heart.value = 3
        _answerList.value = IntArray(9)
        _eventGameFinish.value = false
        Log.i("GameViewModel", "GameViewModel created!")
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }

        timer.start()
    }
    fun randomAnswer(){
        val random = Random()
        var randomInt: Int
        var pickedInt: MutableSet<Int> = mutableSetOf()
        fun rand(from: Int, to: Int): Int{
            do{
                randomInt = random.nextInt(to - from) + from
            }while(pickedInt.contains(randomInt))

            pickedInt.add(randomInt)
            return randomInt
        }
        _answerList.value = IntArray(9)
        for(i in 0..8){
            var n = rand(10,100)
            answerList.value?.set(i, n)
            //answerList[i] = n
        }
        answerGame = answerList.value?.min()

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        timer.cancel()
    }
    fun onCorrect(){
        _score.value = (_score.value)?.plus(10)

    }
    fun onInCorrect(){
        _heart.value = (_heart.value)?.minus(1)
        if(_heart.value == 0) onGameFinish()
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }
    fun onCheckAnswer(value:Int):Boolean{
        if(answerList.value?.get(value) == answerGame){
           onCorrect()
            return true
        }
        else {
            onInCorrect()
            return false
        }
    }
    fun Gameplay (value:Int){
        if(onCheckAnswer(value)){
            randomAnswer()
        }
    }

}