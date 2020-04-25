package com.example.exercisetimer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercisetimer.model.ExerciseTimer

@Database(entities = [ExerciseTimer::class], version = 1, exportSchema = false)
abstract class ExerciseTimerDatabase : RoomDatabase(){

    abstract val exerciseTimerDao: ExerciseTimerDao
    companion object{
        @Volatile
        private var INSTANCE: ExerciseTimerDatabase? = null

        fun getInstance(context: Context): ExerciseTimerDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExerciseTimerDatabase::class.java,
                        "ExerciseTimerDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}