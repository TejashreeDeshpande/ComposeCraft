package com.example.composecraft.presentation.components.easy

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun IconButtonRow(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                Toast.makeText(
                    context, "Clicked Share",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Share")
        }
        Button(onClick = {
            Toast.makeText(
                context, "Clicked Delete",
                Toast.LENGTH_SHORT
            ).show()
        }, modifier = Modifier.weight(1f)) {
            Text(text = "Delete")
        }
        Button(onClick = {
            Toast.makeText(
                context, "Clicked Bookmark",
                Toast.LENGTH_SHORT
            ).show()
        }, modifier = Modifier.weight(1f)) {
            Text(text = "Bookmark")
        }
    }
}