package com.example.exercisetimer.timerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercisetimer.database.ExerciseTimerDao
import com.example.exercisetimer.model.ExerciseTimer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimerListViewModel(private val exerciseTimerDao: ExerciseTimerDao) : ViewModel() {
    val timers = exerciseTimerDao.getAllTimers()
    val timersDesc: LiveData<String>
    get() = Transformations.map(timers){it.joinToString(", ")}

    private suspend fun deleteTimer(timer: ExerciseTimer) = withContext(Dispatchers.IO){
        exerciseTimerDao.deleteTimer(timer)
    }

    fun onDeleteTimer(timer: ExerciseTimer) = viewModelScope.launch {
        deleteTimer(timer)
    }
}
