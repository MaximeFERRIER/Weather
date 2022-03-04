package fr.droidfactory.weather.features.cities.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import fr.droidfactory.weather.R

@Composable
fun NewCityDialog(onCityAdded: (String) -> Unit, onDialogClose: () -> Unit) {
    Dialog(onDismissRequest = {}) {
        Card(elevation = 8.dp, shape = MaterialTheme.shapes.medium) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                val city = remember { mutableStateOf("") }
                DialogTitle(title = stringResource(id = R.string.dialog_title_add_city))
                DialogEditText(city = city, onCityAdded = onCityAdded)
                Spacer(modifier = Modifier.height(24.dp))
                DialogButtons(city = city.value, onCityAdded = onCityAdded, onDialogClose = onDialogClose)
            }
        }
    }
}

@Composable
private fun DialogTitle(title: String) {
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = title,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
private fun DialogEditText(city: MutableState<String>, onCityAdded: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = city.value,
        onValueChange = { newValue ->
            city.value = newValue
        },
        label = {
            Text(text = stringResource(id = R.string.dialog_edittext_hint_add_city), style = MaterialTheme.typography.caption)
        },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true,
        isError = city.value.isBlank(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onCityAdded(city.value)
        })
    )
}

@Composable
private fun DialogButtons(city: String, onCityAdded: (String) -> Unit, onDialogClose: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = { onDialogClose() }) {
            Text(
                text = stringResource(id = R.string.dialog_cancel),
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        TextButton(onClick = { onCityAdded(city) }) {
            Text(
                text = stringResource(id = R.string.dialog_confirm_add_city),
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Preview
@Composable
private fun NewCityDialogPreview() {
    NewCityDialog({}, {})
}