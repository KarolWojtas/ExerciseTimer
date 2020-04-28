package com.example.exercisetimer.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseTimer")
data class ExerciseTimer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "exerciseDuration")
    var exerciseDuration: Int = 0,
    @ColumnInfo(name = "exerciseShortBreak")
    var exerciseShortBreak: Int? = 0,
    @ColumnInfo(name = "intervalBreakDuration")
    var intervalBreakDuration: Int = 0,
    @ColumnInfo(name = "explicitResume")
    var explicitResume: Boolean = false,
    @ColumnInfo(name = "created")
    var created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "intervals")
    var intervals: Int = 1,
    @ColumnInfo(name = "intervalExercises")
    var intervalExercises: Int = 1
): Parcelable{
    @SuppressLint("ParcelClassLoader")
    constructor(parcel: Parcel) : this() {
        with(parcel){
            id = readLong()
            name = readString()
            exerciseDuration = readInt()
            exerciseShortBreak = readInt()
            intervalBreakDuration = readInt()
            explicitResume = readValue(null) as Boolean
            created = readLong()
            intervals = readInt()
            intervalExercises = readInt()
        }
    }
    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.apply {
            writeLong(id)
            writeString(name)
            writeInt(exerciseDuration)
            writeInt(exerciseShortBreak?:0)
            writeInt(intervalBreakDuration)
            writeValue(explicitResume)
            writeLong(created)
            writeInt(intervals)
            writeInt(intervalExercises)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExerciseTimer> {
        override fun createFromParcel(parcel: Parcel): ExerciseTimer {
            return ExerciseTimer(parcel)
        }

        override fun newArray(size: Int): Array<ExerciseTimer?> {
            return arrayOfNulls(size)
        }
    }
}


enum class ExerciseTimerPhases(val label: String){
    START("Start"),
    EXERCISE("Cwiczenie"),
    SHORT_BREAK("Przerwa"),
    INTERVAL_BREAK("Długa przerwa"),
    FINISHED("Zakończono")
}