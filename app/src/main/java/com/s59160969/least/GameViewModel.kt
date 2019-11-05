package com.s59160969.least

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val answerList: IntArray = IntArray(9)
    var answerGame = answerList.min()

    init {
        _score.value =  0
        _heart.value = 3
        Log.i("GameViewModel", "GameViewModel created!")
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
        for(i in 0..8){
            var n = rand(10,100)
            answerList[i] = n
        }
       answerGame = answerList.min()
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
    fun onCorrect(){
        _score.value = (_score.value)?.plus(10)

    }
    fun onInCorrect(){
        _heart.value = (_heart.value)?.minus(1)
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }
}