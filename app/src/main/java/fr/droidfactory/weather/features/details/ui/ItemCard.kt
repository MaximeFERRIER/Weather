package fr.droidfactory.weather.features.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
internal fun ItemCard(modifier: Modifier, icon: ImageVector, title: String, data: String) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colors.onPrimary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onPrimary
            )

            Text(
                text = data,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}