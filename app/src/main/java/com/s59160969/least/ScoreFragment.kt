package com.s59160969.least


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.s59160969.least.databinding.FragmentGameBinding
import com.s59160969.least.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentScoreBinding>(inflater,
            R.layout.fragment_score,container,false)

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).scoreGame)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScoreViewModel::class.java)

        viewModel.score.observe(this, Observer { newScore ->
            binding.pointText.text = newScore.toString()
        })

        viewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
                viewModel.onPlayAgainComplete()
            }
        })

        viewModel.eventHome.observe(this, Observer { home ->
            if (home) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToHomeFragment())
                viewModel.onHomeinComplete()
            }
        })

        viewModel.eventPost.observe(this, Observer { post ->
            if (post) {
                shareSuccess()
                viewModel.onPostinComplete()
            }
        })

        binding.apply {
            playAginButton.setOnClickListener{viewModel.onPlayAgain()}
            homeButton.setOnClickListener {viewModel.onHome()}
            postScoreButton.setOnClickListener {viewModel.onPost()}
        }

        return binding.root
    }

    //create share
    private fun getShareIntent() : Intent {
        val args = ScoreFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.shareText, args.scoreGame))
        return shareIntent
    }
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

}
