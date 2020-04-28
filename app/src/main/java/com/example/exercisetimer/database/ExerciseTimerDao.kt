package com.example.exercisetimer.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.exercisetimer.model.ExerciseTimer

@Dao
interface ExerciseTimerDao {
    @Query("select * from ExerciseTimer order by id desc")
    fun getAllTimers(): LiveData<List<ExerciseTimer>>

    @Insert
    fun insertTimer(timer: ExerciseTimer)

    @Delete
    fun deleteTimer(timer: ExerciseTimer)

    @Update
    fun updateTimer(timer: ExerciseTimer)
}