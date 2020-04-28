package com.example.exercisetimer.timeredit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercisetimer.database.ExerciseTimerDao
import com.example.exercisetimer.model.ExerciseTimer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimerEditViewModel(val exerciseTimerDao: ExerciseTimerDao) : ViewModel() {
    val exerciseDuration = MutableLiveData<String>()
    val exerciseShortBreak = MutableLiveData<String>()
    val intervalBreakDuration = MutableLiveData<String>()
    val explicitResume = MutableLiveData<Boolean>()
    val intervals = MutableLiveData<String>()
    val intervalExercises = MutableLiveData<String>()

    val exerciseTimerModel: ExerciseTimer
    get() {
        return ExerciseTimer(
            exerciseDuration = exerciseDuration.value?.toInt()?:0,
            exerciseShortBreak = exerciseShortBreak.value?.toInt()?:0,
            intervalBreakDuration = intervalBreakDuration.value?.toInt()?:0,
            intervalExercises =  intervalExercises.value?.toInt()?:0,
            intervals = intervals.value?.toInt()?:0,
            explicitResume = explicitResume.value?:false
        )
    }

    private suspend fun saveTimer(name: String? = "random") = withContext(Dispatchers.IO){
        val timer = exerciseTimerModel
        timer.name = name
        exerciseTimerDao.insertTimer(timer)
    }

    fun onSaveTimer(name: String) = viewModelScope.launch {
        saveTimer(name)
    }
}
