package com.example.exercisetimer.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.exercisetimer.model.ExerciseTimer
import com.example.exercisetimer.model.ExerciseTimerPhases
import kotlinx.coroutines.*

class ExerciseViewModel : ViewModel(){

    private var timerDefinition: ExerciseTimer? = null
    private var _timerStatus = MutableLiveData<ExerciseTimerPhases>()
    private var currentInterval = 0
    private var currentExercise = 0
    private var isPaused = false
    val timerStatus: LiveData<ExerciseTimerPhases>
        get() = _timerStatus
    val timerStatusLabel: LiveData<String>
        get() = Transformations.map(_timerStatus){it.label}

    private val _timer = MutableLiveData<Int>()

    private val timerJob = Job()

    private val timerScope = CoroutineScope(Dispatchers.Main + timerJob)

    val timer: LiveData<Int>
        get() = _timer

    fun startTimer(timerDefinition: ExerciseTimer){
        this.timerDefinition = timerDefinition
        /**
         * initial settings assignment
         */
        var nextSettings: Pair<ExerciseTimerPhases, Int?> = ExerciseTimerPhases.EXERCISE to timerDefinition.exerciseDuration
        timerScope.launch {
            while (_timerStatus.value != ExerciseTimerPhases.FINISHED){
                /**
                 * updated status
                 */
                _timerStatus.value = nextSettings.first
                /**
                 * perform countdown
                 */
                countDown(nextSettings.second?:0)
                /**
                 * new settings are stored before exercise / interval increment
                 */
                nextSettings = getNextStatus(_timerStatus.value, timerDefinition)
                /**
                 * increment interval / exercise
                 */
                if(currentExercise < timerDefinition.intervalExercises && currentInterval < timerDefinition.intervals){
                    currentExercise += 1
                } else if(currentInterval < timerDefinition.intervals){
                    currentExercise = 0
                    currentInterval += 1
                } else {
                    _timerStatus.value = ExerciseTimerPhases.FINISHED
                }
            }
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
            _timer.value = if(time > 0 )time - 1 else 0

        }
    }

    private fun getNextStatus(currentStatus: ExerciseTimerPhases?, timerDefinition: ExerciseTimer): Pair<ExerciseTimerPhases, Int?> {
        return when(currentStatus){
            null -> ExerciseTimerPhases.EXERCISE to timerDefinition.exerciseDuration
            ExerciseTimerPhases.EXERCISE -> if (currentInterval == timerDefinition.intervals && currentExercise == timerDefinition.intervalExercises){
                ExerciseTimerPhases.FINISHED to 0
            } else if (currentExercise == timerDefinition.intervalExercises){
                ExerciseTimerPhases.INTERVAL_BREAK to timerDefinition.intervalBreakDuration
            } else if (timerDefinition.exerciseShortBreak?:0 > 0){
                ExerciseTimerPhases.SHORT_BREAK to timerDefinition.exerciseShortBreak
            } else {
                ExerciseTimerPhases.EXERCISE to timerDefinition.exerciseDuration
            }
            ExerciseTimerPhases.SHORT_BREAK, ExerciseTimerPhases.INTERVAL_BREAK -> ExerciseTimerPhases.EXERCISE to timerDefinition.exerciseDuration
            else -> ExerciseTimerPhases.FINISHED to 0
        }
    }
}