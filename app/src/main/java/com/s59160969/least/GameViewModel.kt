package com.s59160969.least

import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {
    var score = 0
    var scoreShow= "SCORE : $score"
    var heart = 3
    val answerList: IntArray = IntArray(9)
    var answerGame = answerList.min()

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
            answerList[i] = n
        }
    }
}