package com.example.composecraft.presentation.features.flightstatus.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FlightStatusTheme


@Preview(
    name = "Default Text Field",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    //backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AppTextField_DefaultPreview() {
    FlightStatusTheme {
        AppTextField(
            value = "Tejashree Deshpande",
            onValueChange = {},
            label = "Full Name",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "Error Text Field",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AppTextField_ErrorPreview() {
    FlightStatusTheme {
        AppTextField(
            value = "tejashree@gmail",
            onValueChange = {},
            label = "Email",
            error = "Please enter a valid email address",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "Search Text Field",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AppTextField_SearchPreview() {
    FlightStatusTheme {
        AppTextField(
            value = "Paris",
            onValueChange = {},
            label = "Search Flights",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "Password Text Field",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AppTextField_PasswordPreview() {
    FlightStatusTheme {
        AppTextField(
            value = "Password123",
            onValueChange = {},
            label = "Password",
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Show Password"
                    )
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    singleLine: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            isError = error != null,
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            supportingText = {
                error?.let { AppErrorText(it) }
            },
            shape = MaterialTheme.shapes.medium
        )
    }
}