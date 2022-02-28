package fr.droidfactory.weather.features.cities.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemCity(city: String, onCityClicked: () -> Unit) {
 val color = getRandomColor()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color)
            .clickable { onCityClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleLetter(city = city, color = color)

        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

        Text(
            text = city,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 48.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun CircleLetter(city: String, color: Color) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .background(shape = CircleShape, color = MaterialTheme.colors.surface)
            .size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = city[0].uppercase(),
            textAlign = TextAlign.Center,
            color = color,
            fontSize = 34.sp
        )
    }
}

private fun getRandomColor() = Color(
    red = (0..255).random(),
    green = (0..255).random(),
    blue = (0..255).random(),

    )