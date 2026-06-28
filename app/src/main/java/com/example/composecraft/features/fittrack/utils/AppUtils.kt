package com.example.composecraft.features.fittrack.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object AppUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedTodayDate(): String {
        val today = LocalDate.now()
        // "EEEE" is full day (Monday), "MMM" is short month (Feb), "d" is day number (20)
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMM d", Locale.getDefault())
        return today.format(formatter)
    }

}