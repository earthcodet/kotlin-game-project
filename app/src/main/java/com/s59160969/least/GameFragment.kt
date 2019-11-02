package com.s59160969.least


import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.s59160969.least.databinding.FragmentGameBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var timeGame: CountDownTimer
    var score = 0
    var scoreShow= "SCORE : $score"
    var heart = 3
    var level = 1
    val answerList: IntArray = IntArray(9)
    var answerGame = answerList.min()
    private val DONE = 0L
    private val ONE_SECOND = 1000L
    var COUNTDOWN_TIME = 15000L
    var currentTimeString = DateUtils.formatElapsedTime(COUNTDOWN_TIME)
    private lateinit var binding: FragmentGameBinding


    fun timeCountGame (){
        timeGame = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                COUNTDOWN_TIME = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                COUNTDOWN_TIME = DONE

                //goToScore()
            }
        }

        timeGame.start()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,container,false)
        binding.apply {

            levelText.text = ""+levelText.text+ level
            timeText.text = ""+timeText.text+ currentTimeString
            play1button.setOnClickListener{checkAnswer(0)}
            play2button.setOnClickListener{checkAnswer(1)}
            play3button.setOnClickListener{checkAnswer(2)}
            play4button.setOnClickListener{checkAnswer(3)}
            play5button.setOnClickListener{checkAnswer(4)}
            play6button.setOnClickListener{checkAnswer(5)}
            play7button.setOnClickListener{checkAnswer(6)}
            play8button.setOnClickListener{checkAnswer(7)}
            play9button.setOnClickListener{checkAnswer(8)}
           endGameText.setOnClickListener {
               goToScore()
          }
        }
        timeCountGame()
        newGame()

        return binding.root
    }
    fun newGame(){
        randomAnswer()
        setGameNumber()
    }
    fun setGameNumber (){
        binding.apply {
            play1button.text = ""+answerList[0]
            play2button.text = ""+answerList[1]
            play3button.text = ""+answerList[2]
            play4button.text = ""+answerList[3]
            play5button.text = ""+answerList[4]
            play6button.text = ""+answerList[5]
            play7button.text = ""+answerList[6]
            play8button.text = ""+answerList[7]
            play9button.text = ""+answerList[8]
        }
        answerGame = answerList.min()
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
            answerList[i] = n
        }
    }
    fun checkAnswer(value:Int){
        if(answerList[value] == answerGame){
            score += 10
            scoreShow= "Score : $score"
            updateScoreText()
            newGame()
        }
        else {
            heart -= 1
            checkheart()
        }
    }
    private fun updateScoreText() {
        binding.scoreText.text = scoreShow
    }
    fun checkheart(){
        if(heart == 2){
            binding.heart3.setImageResource(R.drawable.heartlow)
        }
        if(heart == 1){
            binding.heart2.setImageResource(R.drawable.heartlow)
        }
        if(heart == 0){
            binding.heart1.setImageResource(R.drawable.heartlow)
        }
        else{
            goToScore()
        }
    }
    fun goToScore(){
        findNavController()
            .navigate(GameFragmentDirections.actionGameFragmentToScoreFragment())
    }
}
