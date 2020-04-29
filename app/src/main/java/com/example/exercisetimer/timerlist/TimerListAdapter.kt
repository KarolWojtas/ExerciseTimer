package com.example.exercisetimer.timerlist

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetimer.R
import com.example.exercisetimer.databinding.TimerItemBinding
import com.example.exercisetimer.model.ExerciseTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimerListAdapter(private val clickListener: TimerItemClickListener): ListAdapter<DataItem, RecyclerView.ViewHolder>(TimerDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TimerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TimerViewHolder -> {
                val timer = getItem(position) as DataItem.Timer
                holder.bind(timer, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    fun setList(list: List<ExerciseTimer>?){
        CoroutineScope(Dispatchers.Default).launch {
            val items = when(list){
                null -> mutableListOf()
                else -> list.map { DataItem.Timer(it) }
            }
            withContext(Dispatchers.Main){
                submitList(items)
            }
        }
    }
}

class TimerDiffCallback: DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem{
    abstract val id: Long
    data class Timer(var exerciseTimer: ExerciseTimer): DataItem(){
        override val id = exerciseTimer.id
    }
}

class TimerViewHolder private constructor(val binding: TimerItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item: DataItem.Timer, clickListener: TimerItemClickListener){
        binding.clickListener = clickListener
        binding.timer = item.exerciseTimer
    }
    companion object{
        fun from(parent: ViewGroup): TimerViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<TimerItemBinding>(layoutInflater, R.layout.timer_item, parent, false)
            return TimerViewHolder(binding)
        }
    }
}

interface TimerItemClickListener{
    fun onDelete(timer: ExerciseTimer)
}

@BindingAdapter("numberToString")
fun numberToString(view: TextView?, number: Number){
    view?.let {
        it.text = number.toString()
    }
}