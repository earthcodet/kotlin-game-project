package com.s59160969.least


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.s59160969.least.database.LeastDatabase
import com.s59160969.least.databinding.FragmentHistoryBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */

class HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,
            R.layout.fragment_history,container,false)
        binding.homeButton.setOnClickListener {view:View ->
                    view.findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
                }

        val application = requireNotNull(this.activity).application
        val dataSource = LeastDatabase.getInstance(application).leastDatabaseDAO

        val viewModelFactory = HistoryViewModelFactory(dataSource, application)

        val historyViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(HistoryViewModel::class.java)

        val adapter = HistoryAdaptor()
        binding.scoreList.adapter = adapter
        historyViewModel.scores.observe(viewLifecycleOwner, Observer {
            it?.let {
                //adapter.addHeaderAndSubmitList(it)
                adapter.submitList(it)
                resetIndexScore()
            }
        })

        binding.lifecycleOwner  = this
        binding.historyViewModel = historyViewModel

        historyViewModel.showSnackbarEvent.observe(this, Observer {
            if(it == true){
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_database),
                    Snackbar.LENGTH_SHORT
                ).show()
                historyViewModel.doneShowingSnackbar()
            }
        })

        return binding.root
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
