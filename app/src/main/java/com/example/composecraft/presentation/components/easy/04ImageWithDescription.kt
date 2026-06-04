package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.R

@Composable
fun ImageWithDescription(
    imageId: Int,
    imageDescription: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().background(Color.White)) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Image",
            modifier = Modifier.size(100.dp)
        )
        Text(text = imageDescription)
    }
}

@Preview
@Composable
fun ImageWithDescriptionPreview() {
    ImageWithDescription(
        imageId = R.drawable.dog,
        imageDescription = "Favorite Image"
    )
}