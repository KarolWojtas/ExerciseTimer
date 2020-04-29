package com.example.exercisetimer.model

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.exercisetimer.R

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

@BindingAdapter("numberToString")
fun numberToString(view: TextView?, number: Number){
    view?.let {
        it.text = number.toString()
    }
}

@BindingAdapter("numberTimes")
fun numberTimes(view: TextView?, number: Number){
    view?.let {
        it.text = view.resources.getString(R.string.exerciseTimes, number.toInt())
    }
}