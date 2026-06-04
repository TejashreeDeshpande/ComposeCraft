package com.example.composecraft.presentation.components.easy

import android.annotation.SuppressLint
import kotlin.math.abs

@SuppressLint("DefaultLocale")
fun Double.toDisplayCoord(): String {
    val sign = if (this >= 0) "+" else "-"
    val formattedValue = String.format("%.4f", abs(this))
    return "$sign$formattedValue°"
}