package com.example.exercisetimer.timeredit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercisetimer.database.ExerciseTimerDao
import java.lang.IllegalArgumentException

class TimerEditViewModelFactory(private val exerciseTimerDao: ExerciseTimerDao) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TimerEditViewModel::class.java)){
            return TimerEditViewModel(exerciseTimerDao) as T
        } else {
            throw IllegalArgumentException();
        }
    }
}