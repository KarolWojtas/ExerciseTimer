package com.example.exercisetimer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetimer.model.ExerciseTimer
import com.example.exercisetimer.model.ExerciseTimerPhases
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class TimerViewModel : ViewModel(){

    private var timerDefinition: ExerciseTimer? = null
    private var _timerStatus = MutableLiveData<String>()
    private var currentInterval = 0
    private var currentExercise = 0
    private var currentStatus: ExerciseTimerPhases? = null
    private var isPaused = false
    val timerStatus: LiveData<String>
        get() = _timerStatus

    private val _timer = MutableLiveData<Int>()

    private val timerJob = Job()

    private val timerScope = CoroutineScope(Dispatchers.Main + timerJob)

    val timer: LiveData<Int>
        get() = _timer

    fun startTimer(timerDefinition: ExerciseTimer){
        currentStatus = null
        this.timerDefinition = timerDefinition
        timerScope.launch {
            while (currentStatus != ExerciseTimerPhases.FINISHED){
                val (status, countdown) = getNextStatus(currentStatus, timerDefinition)
                currentStatus = status
                _timerStatus.value = status.label
                countDown(countdown?:0)
                if(currentExercise < timerDefinition.intervalExercises){
                    currentExercise += 1
                } else if(currentInterval < timerDefinition.intervals){
                    currentExercise = 0
                    currentInterval += 1
                }
            }
            currentStatus = ExerciseTimerPhases.FINISHED
            _timerStatus.value = ExerciseTimerPhases.FINISHED.label
        }
    }

    fun toggleStatus(){
        // todo
    }

    private suspend fun countDown(startTime: Int) {
        _timer.value = startTime - 1
        for (time in startTime downTo 0){
            if(time < startTime){
                delay(1000)
            }
            _timer.value = time - 1

        }
    }

    private fun getNextStatus(currentStatus: ExerciseTimerPhases?, timerDefinition: ExerciseTimer): Pair<ExerciseTimerPhases, Int?> {
        return when(currentStatus){
            null -> ExerciseTimerPhases.EXERCISE to timerDefinition.exerciseDuration
            ExerciseTimerPhases.EXERCISE -> if (currentInterval == timerDefinition.intervals){
                ExerciseTimerPhases.FINISHED to 0
            } else if (currentExercise == timerDefinition.intervalExercises){
                ExerciseTimerPhases.INTERVAL_BREAK to timerDefinition.intervalBreakDuration
            } else if (timerDefinition.exerciseShortBreak?:0 > 0){
                ExerciseTimerPhases.SHORT_BREAK to timerDefinition.exerciseShortBreak
            } else {
                ExerciseTimerPhases.EXERCISE to 0
            }
            ExerciseTimerPhases.SHORT_BREAK, ExerciseTimerPhases.INTERVAL_BREAK -> ExerciseTimerPhases.EXERCISE to timerDefinition.exerciseDuration
            else -> ExerciseTimerPhases.FINISHED to 0
        }
    }
}