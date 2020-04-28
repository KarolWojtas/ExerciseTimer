package com.example.exercisetimer.timerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercisetimer.database.ExerciseTimerDao
import java.lang.IllegalArgumentException

class TimerListViewModelFactory(private val exerciseTimerDao: ExerciseTimerDao) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TimerListViewModel::class.java)){
            return TimerListViewModel(exerciseTimerDao) as T
        } else {
            throw IllegalArgumentException();
        }
    }
}