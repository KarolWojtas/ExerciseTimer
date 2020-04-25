package com.example.exercisetimer.timeredit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.exercisetimer.R
import com.example.exercisetimer.databinding.TimerEditFragmentBinding
import com.example.exercisetimer.viewmodel.TimerViewModel

class TimerEdit : Fragment() {

    private lateinit var binding: TimerEditFragmentBinding
    private lateinit var viewModel: TimerEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.timer_edit_fragment, container, false)
        viewModel = ViewModelProvider(this).get(TimerEditViewModel::class.java)
        binding.timerEditViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.startBtn.setOnClickListener {
            val action = TimerEditDirections.actionTimerEditToExerciseFragment(viewModel.exerciseTimerModel)
            findNavController().navigate(action)
        }
        return binding.root
    }

}
