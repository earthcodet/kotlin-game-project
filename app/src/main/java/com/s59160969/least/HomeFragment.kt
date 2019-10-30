package com.s59160969.least


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.s59160969.least.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)
        binding.startGameButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
        }
        binding.chartButton.setOnClickListener{
                view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
        return binding.root
    }


}
