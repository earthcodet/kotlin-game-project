package com.s59160969.least


import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Transformations
import androidx.navigation.fragment.findNavController
import com.s59160969.least.databinding.FragmentGameBinding
import com.s59160969.least.databinding.FragmentHomeBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private val timer: CountDownTimer
    var score = 0
    var heart = 3
    var level = 1
    val answer: IntArray = IntArray(9)
    private val DONE = 0L
    private var status = false
    private val ONE_SECOND = 1000L
    var COUNTDOWN_TIME = 15000L
    var currentTimeString = DateUtils.formatElapsedTime(COUNTDOWN_TIME)
    private lateinit var binding: FragmentGameBinding
    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                COUNTDOWN_TIME = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                    COUNTDOWN_TIME = DONE

                //goToScore()
            }
        }

        timer.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,container,false)
        binding.apply {
            scroeText.text = ""+score
            levelText.text = ""+levelText.text+ level
            timeText.text = ""+timeText.text+ currentTimeString
           endGameText.setOnClickListener {
               goToScore()
          }
        }
        return binding.root
    }
    fun changeStatus(){
        status = !status
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
            answer[i] = n
        }
    }
//    fun checkheart(){
//        if(heart == 2){
//            binding.heart3.setImageResource(R.drawable.heartlow)
//        }
//        if(heart == 1){
//            binding.heart2.setImageResource(R.drawable.heartlow)
//        }
//        if(heart == 0){
//            binding.heart1.setImageResource(R.drawable.heartlow)
//        }
//        else{
//            goToScore()
//        }
//    }
    fun goToScore(){
        findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
    }
}
