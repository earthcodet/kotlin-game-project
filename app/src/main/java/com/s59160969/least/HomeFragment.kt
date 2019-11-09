package com.s59160969.least


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.s59160969.least.databinding.FragmentHomeBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)
        binding.startGameButton.setOnClickListener { view : View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
        }
        binding.chartButton.setOnClickListener{
                view : View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHistoryFragment())
        }
        binding.imageView.setOnClickListener { view:View ->
            Snackbar.make(view, "Welcome to Least", Snackbar.LENGTH_SHORT).show()
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
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
