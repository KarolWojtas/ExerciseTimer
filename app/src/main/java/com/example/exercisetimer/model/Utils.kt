package com.example.exercisetimer.model

import android.content.res.Resources
import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.exercisetimer.R

@BindingAdapter("statusColor")
fun statusColor(view: View, status: ExerciseTimerPhases?){
    view.setBackgroundColor(when(status){
        ExerciseTimerPhases.SHORT_BREAK -> ContextCompat.getColor(view.context, R.color.lightYellow)
        ExerciseTimerPhases.INTERVAL_BREAK -> ContextCompat.getColor(view.context, R.color.lightGreen)
        ExerciseTimerPhases.EXERCISE -> ContextCompat.getColor(view.context, R.color.lightRed)
        ExerciseTimerPhases.START -> Color.WHITE
        ExerciseTimerPhases.FINISHED -> ContextCompat.getColor(view.context, R.color.lightBlue)
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

@BindingAdapter("runningStatusDrawable")
fun runningStatusDrawable(view: ImageButton?, paused: Boolean){
    view?.let {
        view.background = ContextCompat.getDrawable(view.context, if(paused) R.drawable.ic_play_arrow_black_32dp else R.drawable.ic_pause_black_18dp)
    }
}