package com.s59160969.least


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.s59160969.least.databinding.FragmentGameBinding
import com.s59160969.least.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass.
 */

class HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,
            R.layout.fragment_history,container,false)
            binding.homeButton.setOnClickListener { view:View ->
                binding.homeButton.setOnClickListener {view:View ->
                    view.findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
                }
            }
        return binding.root
    }


}
