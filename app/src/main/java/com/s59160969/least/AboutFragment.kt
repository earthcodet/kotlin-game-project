package com.s59160969.least

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.s59160969.least.databinding.FragmentAboutBinding
import com.s59160969.least.databinding.FragmentGameBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_about, container,false)
        binding.homeButton.setOnClickListener {view:View ->
            view.findNavController().navigate(R.id.action_aboutFragment_to_homeFragment)
        }
        return binding.root
    }
}
