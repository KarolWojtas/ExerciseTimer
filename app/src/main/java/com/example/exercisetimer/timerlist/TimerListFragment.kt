package com.example.exercisetimer.timerlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.exercisetimer.R
import com.example.exercisetimer.databinding.TimerListFragmentBinding
import com.example.exercisetimer.model.ExerciseTimer

class TimerListFragment : Fragment() {

    private lateinit var binding: TimerListFragmentBinding
    private lateinit var viewModel: TimerListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.timer_list_fragment, container, false)
        viewModel = ViewModelProvider(this).get(TimerListViewModel::class.java)
        binding.timerListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.newTimerBtn.setOnClickListener {
            val action = TimerListFragmentDirections.actionTimerListFragmentToTimerEdit()
            findNavController().navigate(action)
        }

        return binding.root
    }

}
