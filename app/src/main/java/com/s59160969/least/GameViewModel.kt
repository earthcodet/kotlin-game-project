package com.s59160969.least

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {
    var _score = MutableLiveData<Int>()
    var _heart = MutableLiveData<Int>()
    var _answerList = MutableLiveData<IntArray>()
    //val _answerList: IntArray = IntArray(9)
    var _answerGame = _answerList.value?.min()

    init {
        _score.value =  0
        _heart.value = 3
        _answerList.value =  IntArray(9)
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
            var n = rand(1,100)
            _answerList.value?.set(i, n)
        }
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
}