package com.example.exercisetimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercisetimer.databinding.ActivityMainBinding
import com.example.exercisetimer.exercise.ExerciseViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var exerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }
}
