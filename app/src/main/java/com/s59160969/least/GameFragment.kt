package com.s59160969.least


import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.s59160969.least.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var timeGame: CountDownTimer
    private val DONE = 0L
    private val ONE_SECOND = 1000L
    var COUNTDOWN_TIME = 15000L
    var currentTimeString = DateUtils.formatElapsedTime(COUNTDOWN_TIME)
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,container,false)
        Log.i("GameFragment", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.apply {
            timeText.text = ""+timeText.text+ currentTimeString
            play1button.setOnClickListener{onCheckAnswer(0)}
            play2button.setOnClickListener{onCheckAnswer(1)}
            play3button.setOnClickListener{onCheckAnswer(2)}
            play4button.setOnClickListener{onCheckAnswer(3)}
            play5button.setOnClickListener{onCheckAnswer(4)}
            play6button.setOnClickListener{onCheckAnswer(5)}
            play7button.setOnClickListener{onCheckAnswer(6)}
            play8button.setOnClickListener{onCheckAnswer(7)}
            play9button.setOnClickListener{onCheckAnswer(8)}
           endGameText.setOnClickListener {
               onEndGame()
          }
        }
        newGame()
        return binding.root
    }
    fun newGame(){
        viewModel.randomAnswer()
        setGameNumber()
    }
    fun setGameNumber (){
        binding.apply {
            play1button.text = ""+viewModel.answerList[0]
            play2button.text = ""+viewModel.answerList[1]
            play3button.text = ""+viewModel.answerList[2]
            play4button.text = ""+viewModel.answerList[3]
            play5button.text = ""+viewModel.answerList[4]
            play6button.text = ""+viewModel.answerList[5]
            play7button.text = ""+viewModel.answerList[6]
            play8button.text = ""+viewModel.answerList[7]
            play9button.text = ""+viewModel.answerList[8]
        }
        viewModel.answerGame = viewModel.answerList.min()
    }
    fun onCheckAnswer(value:Int){
        if(viewModel.answerList[value] == viewModel.answerGame){
            updateScoreText()
            newGame()
        }
        else {
            viewModel.heart -= 1
            onCheckHeart()
        }
    }
    private fun updateScoreText() {
        viewModel.score += 10
        binding.scoreText.text = "Score : ${viewModel.score}"
    }
    fun onCheckHeart(){
        when (viewModel.heart) {
            2 -> binding.heart3.setImageResource(R.drawable.heartlow)
            1 -> binding.heart2.setImageResource(R.drawable.heartlow)
            0 -> binding.heart1.setImageResource(R.drawable.heartlow)
            else -> onEndGame()
        }
    }
    fun onEndGame(){
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score)
        NavHostFragment.findNavController(this).navigate(action)
    }

}
