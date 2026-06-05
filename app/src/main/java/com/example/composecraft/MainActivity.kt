package com.example.composecraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.composecraft.presentation.ProductListScreen
import com.example.composecraft.presentation.components.jun4.CounterWaterIntake
import com.example.composecraft.presentation.components.jun4.SearchBarWithFilterChips
import com.example.composecraft.ui.theme.ComposeCraftTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCraftTheme {
                SearchBarWithFilterChips()
            }
        }
    }
}