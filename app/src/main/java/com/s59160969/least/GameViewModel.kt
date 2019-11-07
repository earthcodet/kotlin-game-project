package com.s59160969.least

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.*
import com.s59160969.least.database.LeastDatabaseDAO
import com.s59160969.least.database.LeastScore
import kotlinx.coroutines.*
import java.util.*

class GameViewModel (val database: LeastDatabaseDAO, application: Application) : AndroidViewModel(application) {
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

    private val _incorrect1 = MutableLiveData<Int>()
    val incorrect1:LiveData<Int>
        get() = _incorrect1

    private val _incorrect2 = MutableLiveData<Int>()
    val incorrect2:LiveData<Int>
        get() = _incorrect2

    var answerGame = answerList.value?.min()
    private val timer: CountDownTimer


    val currentTimeString = Transformations.map(currentTime) { time ->
        "Time : ${DateUtils.formatElapsedTime(time)}"
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


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
        _incorrect1.value = -1
        _incorrect2.value = -1
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
        }
        answerGame = answerList.value?.min()

    }
        fun markIncorrect(value: Int){
            if(_incorrect1.value == -1){
                _incorrect1.value = value
            }else{
                _incorrect2.value = value
            }
        }
        fun resetIncorrect(){
            _incorrect1.value = -1
            _incorrect2.value = -1
        }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        timer.cancel()
        viewModelJob.cancel()
    }
    fun onCorrect(){
        _score.value = (_score.value)?.plus(10)

    }
    fun onIncorrect(){
        _heart.value = (_heart.value)?.minus(1)
        if(_heart.value == 0) onGameFinish()
    }

    fun onGameFinish() {
        InsertData()
        _eventGameFinish.value = true
    }

    fun onCheckAnswer(value:Int):Boolean{
        if(answerList.value?.get(value) == answerGame){
           onCorrect()
            resetIncorrect()
            return true
        }
        else {
            markIncorrect(value)
            onIncorrect()
            return false
        }
    }
    fun Gameplay (value:Int):Int{
        if(onCheckAnswer(value)){
            randomAnswer()
        }
        return value
    }
    fun InsertData(){
        uiScope.launch {
            val newLeastScore = LeastScore()
            insert(newLeastScore)
        }
    }
    private suspend fun insert(score: LeastScore) {
        withContext(Dispatchers.IO){
            score.leastScore = _score.value?:0
            database.insert(score)
        }
    }
}