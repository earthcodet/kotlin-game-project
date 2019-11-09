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
import com.google.android.material.snackbar.Snackbar
import com.s59160969.least.database.LeastDatabase
import com.s59160969.least.databinding.FragmentGameBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,false)
        Log.i("GameFragment", "Called ViewModelProviders.of")
        val application = requireNotNull(this.activity).application
        val dataSource = LeastDatabase.getInstance(application).leastDatabaseDAO

        val viewModelFactory = GameViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(GameViewModel::class.java)

        //viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

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
    override fun onStart() {
        super.onStart()
        Timber.i("onStart Called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate Called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }
}
