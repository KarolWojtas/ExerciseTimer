package com.example.exercisetimer.timerlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.exercisetimer.R
import com.example.exercisetimer.database.ExerciseTimerDatabase
import com.example.exercisetimer.databinding.TimerListFragmentBinding
import com.example.exercisetimer.model.ExerciseTimer
import com.example.exercisetimer.timeredit.TimerEditViewModel
import com.example.exercisetimer.timeredit.TimerEditViewModelFactory

class TimerListFragment : Fragment() {

    private lateinit var binding: TimerListFragmentBinding
    private lateinit var viewModel: TimerListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.timer_list_fragment, container, false)
        val viewModelFactory = TimerListViewModelFactory(ExerciseTimerDatabase.getInstance(requireContext()).exerciseTimerDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TimerListViewModel::class.java)

        binding.timerListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.newTimerBtn.setOnClickListener {
            val action = TimerListFragmentDirections.actionTimerListFragmentToTimerEdit()
            findNavController().navigate(action)
        }

        val adapter = TimerListAdapter(object : TimerItemClickListener{
            override fun onDelete(timer: ExerciseTimer) {
                viewModel.onDeleteTimer(timer)
            }
        })

        binding.timerRecyclerView.adapter = adapter

        viewModel.timers.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setList(it)
            }
        })

        return binding.root
    }

}
