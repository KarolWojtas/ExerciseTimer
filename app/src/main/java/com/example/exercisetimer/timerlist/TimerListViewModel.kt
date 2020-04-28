package com.example.exercisetimer.timerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.exercisetimer.database.ExerciseTimerDao

class TimerListViewModel(private val exerciseTimerDao: ExerciseTimerDao) : ViewModel() {
    val timers = exerciseTimerDao.getAllTimers()
    val timersDesc: LiveData<String>
    get() = Transformations.map(timers){it.joinToString(", ")}
}
