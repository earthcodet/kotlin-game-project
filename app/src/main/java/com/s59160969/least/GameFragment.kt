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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,false)

        Log.i("GameFragment", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)


        viewModel.eventGameFinish.observe(this, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })

        viewModel.randomAnswer()
        binding.lifecycleOwner = this
        binding.gameViewModel = viewModel


        return binding.root
    }


    fun gameFinished(){
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value?:0)
        NavHostFragment.findNavController(this).navigate(action)
    }
}
