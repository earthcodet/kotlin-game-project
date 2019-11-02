package com.s59160969.least


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.s59160969.least.databinding.FragmentGameBinding
import com.s59160969.least.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentScoreBinding>(inflater,
            R.layout.fragment_score,container,false)
        binding.playAginButton.setOnClickListener{
                view : View ->
            view.findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
        }
        binding.homeButton.setOnClickListener {
                view : View ->
            view.findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToHomeFragment())
        }

        return binding.root
    }

}
