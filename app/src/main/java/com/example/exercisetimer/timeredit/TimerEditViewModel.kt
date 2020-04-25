package com.example.exercisetimer.timeredit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetimer.model.ExerciseTimer

class TimerEditViewModel : ViewModel() {
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
}
