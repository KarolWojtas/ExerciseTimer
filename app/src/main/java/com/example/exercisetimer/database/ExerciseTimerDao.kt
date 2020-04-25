package com.example.exercisetimer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.exercisetimer.model.ExerciseTimer

@Dao
interface ExerciseTimerDao {
    @Query("select * from ExerciseTimer order by created desc")
    fun getAllTimers(): LiveData<List<ExerciseTimer>>
}