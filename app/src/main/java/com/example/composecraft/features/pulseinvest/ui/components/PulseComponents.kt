package com.example.composecraft.features.pulseinvest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.theme.*

// ─── Primary Green Button ───────────────────────────────────────────────────
@Composable
fun PulseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier.fillMaxWidth().height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PulseGreen,
            contentColor = Color.Black,
            disabledContainerColor = PulseGreen.copy(alpha = 0.4f)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
        } else {
            Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// ─── Outline Button ─────────────────────────────────────────────────────────
@Composable
fun PulseOutlineButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(52.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PulseGreen),
        border = androidx.compose.foundation.BorderStroke(1.dp, PulseGreen),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
    }
}

// ─── Input Field ─────────────────────────────────────────────────────────────
@Composable
fun PulseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val visualTransformation = if (isPassword)
        androidx.compose.ui.text.input.PasswordVisualTransformation()
    else
        androidx.compose.ui.text.input.VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = PulseTextMuted) },
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PulseGreen,
            unfocusedBorderColor = PulseBorder,
            focusedContainerColor = PulseSurface2,
            unfocusedContainerColor = PulseSurface2,
            focusedTextColor = PulseTextPrimary,
            unfocusedTextColor = PulseTextPrimary,
            cursorColor = PulseGreen
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true
    )
}

// ─── Change Badge ─────────────────────────────────────────────────────────────
@Composable
fun ChangeBadge(value: Double, percent: Double, showSign: Boolean = true) {
    val isPositive = value >= 0
    val color = if (isPositive) PulseGreen else PulseRed
    val sign = if (isPositive) "▲" else "▼"
    Text(
        text = "$sign ${if (showSign && value >= 0) "+" else ""}${"%.2f".format(value)} (${"%.2f".format(percent)}%)",
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
}

// ─── Section Header ──────────────────────────────────────────────────────────
@Composable
fun SectionHeader(title: String, actionText: String? = null, onAction: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        if (actionText != null && onAction != null) {
            Text(actionText, color = PulseGreen, fontSize = 13.sp,
                modifier = Modifier.clickable { onAction() })
        }
    }
}

// ─── Stock Row ────────────────────────────────────────────────────────────────
@Composable
fun StockRow(
    emoji: String,
    name: String,
    subtitle: String,
    price: String,
    change: String,
    isPositive: Boolean,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(PulseSurface2),
            contentAlignment = Alignment.Center
        ) { Text(emoji, fontSize = 18.sp) }
        Column(modifier = Modifier.weight(1f).padding(horizontal = 10.dp)) {
            Text(name, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(subtitle, color = PulseTextSecondary, fontSize = 12.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(price, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(change, color = if (isPositive) PulseGreen else PulseRed, fontSize = 12.sp)
        }
    }
    HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
}

// ─── Card Container ──────────────────────────────────────────────────────────
@Composable
fun PulseCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PulseSurface)
            .padding(16.dp),
        content = content
    )
}

// ─── Screen Scaffold ─────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseScaffold(
    title: String = "",
    showBack: Boolean = false,
    onBack: () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        containerColor = PulseDark,
        snackbarHost = snackbarHost,
        topBar = {
            if (title.isNotEmpty() || showBack) {
                CenterAlignedTopAppBar(
                    title = { Text(title, color = PulseTextPrimary, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        if (showBack) {
                            IconButton(onClick = onBack) {
                                Text("←", color = PulseGreen, fontSize = 20.sp)
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = PulseDark
                    )
                )
            }
        },
        content = content
    )
}

// ─── PIN Dot Row ─────────────────────────────────────────────────────────────
@Composable
fun PinDots(filledCount: Int, total: Int = 6) {
    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        repeat(total) { i ->
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(if (i < filledCount) PulseGreen else PulseSurface2)
                    .border(2.dp, if (i < filledCount) PulseGreen else PulseBorder, CircleShape)
            )
        }
    }
}

// ─── Number Pad ──────────────────────────────────────────────────────────────
@Composable
fun NumPad(onDigit: (String) -> Unit, onBackspace: () -> Unit) {
    val keys = listOf("1","2","3","4","5","6","7","8","9","","0","⌫")
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        keys.chunked(3).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                row.forEach { key ->
                    if (key.isEmpty()) {
                        Spacer(modifier = Modifier.size(68.dp))
                    } else {
                        Box(
                            modifier = Modifier
                                .size(68.dp)
                                .clip(CircleShape)
                                .background(PulseSurface2)
                                .border(1.dp, PulseBorder, CircleShape)
                                .clickable { if (key == "⌫") onBackspace() else onDigit(key) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(key, color = PulseTextPrimary, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

// ─── Toggle Switch ────────────────────────────────────────────────────────────
@Composable
fun PulseSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Black,
            checkedTrackColor = PulseGreen,
            uncheckedThumbColor = PulseTextMuted,
            uncheckedTrackColor = PulseSurface2
        )
    )
}

// ─── Method Selector Card ─────────────────────────────────────────────────────
@Composable
fun MethodCard(emoji: String, title: String, desc: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(PulseSurface)
            .border(2.dp, if (selected) PulseGreen else Color.Transparent, RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(PulseDark),
            contentAlignment = Alignment.Center
        ) { Text(emoji, fontSize = 22.sp) }
        Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
            Text(title, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(desc, color = PulseTextSecondary, fontSize = 12.sp)
        }
        if (selected) {
            Box(
                modifier = Modifier.size(22.dp).clip(CircleShape).background(PulseGreen),
                contentAlignment = Alignment.Center
            ) { Text("✓", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold) }
        }
    }
}

// ─── Tab Row ─────────────────────────────────────────────────────────────────
@Composable
fun PulseTabRow(tabs: List<String>, selected: String, onSelect: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PulseSurface)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tabs.forEach { tab ->
            val isSelected = tab == selected
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(9.dp))
                    .background(if (isSelected) PulseGreen else Color.Transparent)
                    .clickable { onSelect(tab) }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    tab,
                    color = if (isSelected) Color.Black else PulseTextSecondary,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// ─── Sparkline mini chart using Canvas ───────────────────────────────────────
@Composable
fun SparklineChart(data: List<Float>, isPositive: Boolean, modifier: Modifier = Modifier) {
    val color = if (isPositive) PulseGreen else PulseRed
    if (data.isEmpty()) return
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val min = data.min()
        val max = data.max()
        val range = (max - min).takeIf { it > 0 } ?: 1f
        val pts = data.mapIndexed { i, v ->
            androidx.compose.ui.geometry.Offset(
                x = i * (size.width / (data.size - 1).coerceAtLeast(1)),
                y = size.height - ((v - min) / range) * size.height
            )
        }
        val path = androidx.compose.ui.graphics.Path()
        pts.forEachIndexed { i, pt -> if (i == 0) path.moveTo(pt.x, pt.y) else path.lineTo(pt.x, pt.y) }
        drawPath(path, color = color, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()))
    }
}
