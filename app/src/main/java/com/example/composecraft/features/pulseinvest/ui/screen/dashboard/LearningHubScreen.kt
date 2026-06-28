package com.example.composecraft.features.pulseinvest.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.domain.model.CourseStatus
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.LearningViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseButton
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun LearningHubScreen(vm: LearningViewModel, onBack: () -> Unit) {
    val progress by vm.progress.collectAsState()

    PulseScaffold(title = "Learning", showBack = true, onBack = onBack) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            item {
                Spacer(Modifier.height(8.dp))
                // Progress card
                Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp))
                    .background(PulseGreenBg).border(1.dp, Color(0xFF2A4A2A), RoundedCornerShape(14.dp)).padding(16.dp)) {
                    Column {
                        Text("Your Progress", color = PulseTextSecondary, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        Box(modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(PulseGreen).padding(horizontal = 12.dp, vertical = 4.dp)) {
                            Text("⭐ ${progress?.levelTitle ?: "Level 5 Investor"}", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        Spacer(Modifier.height(10.dp))
                        Text("${progress?.currentXp ?: 750} / ${progress?.maxXp ?: 1000} XP", color = PulseTextSecondary, fontSize = 12.sp)
                        Spacer(Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { (progress?.currentXp ?: 750).toFloat() / (progress?.maxXp ?: 1000) },
                            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                            color = PulseGreen, trackColor = PulseSurface2
                        )
                        Spacer(Modifier.height(12.dp))
                        PulseButton("Continue Learning", onClick = {})
                    }
                }
                Spacer(Modifier.height(20.dp))
                Text("Courses", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(10.dp))
            }
            items(progress?.courses ?: emptyList()) { course ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) {
                        Text(course.emoji, fontSize = 22.sp)
                    }
                    Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                        Text(course.title, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Text("${course.durationMinutes} min", color = PulseTextSecondary, fontSize = 12.sp)
                    }
                    when (course.status) {
                        CourseStatus.COMPLETED -> Text("✓ Done", color = PulseTextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        CourseStatus.IN_PROGRESS -> Text("In Progress →", color = PulseGreen, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        CourseStatus.LOCKED -> Text("🔒", fontSize = 16.sp)
                    }
                }
                Spacer(Modifier.height(4.dp))
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
