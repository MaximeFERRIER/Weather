package fr.droidfactory.weather.features.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import fr.droidfactory.weather.R

@Composable
internal fun ItemError(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.error).padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
               , elevation = 8.dp, shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = R.drawable.ic_error,
                        builder = {
                            scale(Scale.FIT)
                            crossfade(1000)
                        }
                    ),
                    contentDescription = errorMessage)

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = errorMessage,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}