package com.example.exercisetimer.timersavedialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.exercisetimer.R
import com.example.exercisetimer.databinding.TimerSaveDialogBinding

class TimerSaveDialog(val listener: TimerSaveDialogListener) : DialogFragment(){
    private lateinit var binding: TimerSaveDialogBinding

    interface TimerSaveDialogListener {
        fun onSave(name: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater
            binding = DataBindingUtil.inflate(inflater, R.layout.timer_save_dialog, null, false)
            builder.setView(binding.root)
                .setPositiveButton("Zapisz"){ dialog, id ->
                    // notify timer edit with entered value
                    listener.onSave(binding.nameInput.text.toString())
                    getDialog()?.cancel()
                }
                .setNegativeButton("Anuluj"
                ) { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}