package com.example.exercisetimer.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.exercisetimer.R
import com.example.exercisetimer.databinding.ExerciseFragmentBinding
import com.example.exercisetimer.model.ExerciseTimer
import java.lang.IllegalArgumentException

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseFragment : Fragment() {

    lateinit var binding: ExerciseFragmentBinding
    lateinit var exerciseViewModel: ExerciseViewModel
    private val args: ExerciseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.exercise_fragment, container, false)

        exerciseViewModel = ViewModelProvider(this, ExerciseViewModelFactory(args.definition)).get(ExerciseViewModel::class.java)

        binding.timerViewModel = exerciseViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.stopBtn.setOnClickListener { exerciseViewModel.stopTimer() }

        binding.statusBtn.setOnClickListener { exerciseViewModel.toggleStatus() }

        return binding.root
    }
}

class ExerciseViewModelFactory(private val timerDefinition: ExerciseTimer): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)){
            return ExerciseViewModel(timerDefinition) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}
