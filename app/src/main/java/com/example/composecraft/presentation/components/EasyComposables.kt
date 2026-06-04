package com.example.composecraft.presentation.components

import android.widget.Switch
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextButton(
    text: String = "",
    buttonText: String = "",
    onClickButton: () -> Unit
) {
    Column {
        Text(text = text)
        Button(onClick = onClickButton) {
            Text(text = buttonText)
        }
    }
}
//----------------------------------------------//
@Preview
@Composable
fun TextButtonPreview() {
    val context = LocalContext.current

    TextButton(
        text = "Hello Compose",
        buttonText = "Click Me",
        onClickButton = {
            Toast.makeText(
                context,
                "Button Clicked!",
                Toast.LENGTH_SHORT
            ).show()
        })
}

@Composable
fun TextFieldView(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Enter name") },
        placeholder = { Text(text = "John Doe") }
    )
}

@Preview
@Composable
fun TextFieldPreview() {

    var text by remember { mutableStateOf("") }
    Column {
        TextFieldView(
            value = text,
            onValueChange = { newValue ->
                text = newValue
            }
        )
        Text(text = "You typed $text")
    }
}
//----------------------------------------------//
@Composable
fun toggleSwitch() {


}