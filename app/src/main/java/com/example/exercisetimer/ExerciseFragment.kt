package com.example.exercisetimer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.exercisetimer.databinding.ExerciseFragmentBinding
import com.example.exercisetimer.databinding.TimerListFragmentBinding
import com.example.exercisetimer.model.ExerciseTimer
import com.example.exercisetimer.viewmodel.TimerViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseFragment : Fragment() {

    lateinit var binding: ExerciseFragmentBinding
    lateinit var timerViewModel: TimerViewModel
    private val args: ExerciseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.exercise_fragment, container, false)
        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        binding.timerViewModel = timerViewModel
        timerViewModel.startTimer(args.definition)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
