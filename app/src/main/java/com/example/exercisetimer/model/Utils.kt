package com.example.exercisetimer.model

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("statusColor")
fun statusColor(view: View, status: ExerciseTimerPhases?){
    view.setBackgroundColor(when(status){
        ExerciseTimerPhases.SHORT_BREAK -> Color.YELLOW
        ExerciseTimerPhases.INTERVAL_BREAK -> Color.GREEN
        ExerciseTimerPhases.EXERCISE -> Color.RED
        ExerciseTimerPhases.START -> Color.WHITE
        ExerciseTimerPhases.FINISHED -> Color.BLUE
        else -> Color.WHITE
    })
}