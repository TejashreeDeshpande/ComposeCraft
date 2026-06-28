package com.example.composecraft.features.pulseinvest.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.components.PulseButton
import com.example.composecraft.features.pulseinvest.ui.components.SparklineChart
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SplashScreen(onGetStarted: () -> Unit, onLogin: () -> Unit, onExit: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PulseDark, PulseSurface, PulseDark))),
        contentAlignment = Alignment.Center
    ) {
        // Exit button in top left
        IconButton(
            onClick = onExit,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Text("✕", color = PulseGreen, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("📈", fontSize = 64.sp)
            Spacer(Modifier.height(16.dp))
            Text("PULSE", fontSize = 42.sp, fontWeight = FontWeight.ExtraBold,
                color = PulseGreen, letterSpacing = 4.sp)
            Text("Invest smarter. Grow better.", fontSize = 14.sp, color = PulseTextMuted,
                letterSpacing = 1.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(32.dp))
            SparklineChart(
                data = listOf(55f, 45f, 50f, 28f, 32f, 15f, 8f),
                isPositive = true,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            )
            Spacer(Modifier.height(32.dp))
            Text(
                "AI insights, real-time data,\nand smart tools to grow your wealth.",
                fontSize = 15.sp, color = PulseTextSecondary,
                textAlign = TextAlign.Center, lineHeight = 22.sp
            )
            Spacer(Modifier.height(48.dp))
            PulseButton("Get Started", onClick = onGetStarted)
            Spacer(Modifier.height(12.dp))
            androidx.compose.material3.TextButton(onClick = onLogin) {
                Text("Log In", color = PulseGreen, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
