package com.example.project.signal

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.data.entities.Signal
import com.example.project.data.entities.User
import com.example.project.user.UserViewModel

enum class Field {
    Signal1, Signal2, Signal3
}
@Composable
fun SignalList() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignalForm(modifier: Modifier = Modifier, viewModel: SignalEntryViewModel = viewModel()) {
    val signalsState: Signal = viewModel.signalState
//TODO reikia pagalvot jei input bus visi 000
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Pridėti signalą",
            style = MaterialTheme.typography.titleLarge
        )
        TextField(
            value = signalsState.sensor1,
            onValueChange = {
                viewModel.updateSignalState(signalsState.copy(sensor1 = it), Field.Signal1)
                            },
            label = { Text(text = "wiliboxas1") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            /*isError = true,
            supportingText = { Text(text = "erroras") }*/)
        TextField(value = signalsState.sensor2,
            onValueChange = {
                viewModel.updateSignalState(signalsState.copy(sensor2 = it), Field.Signal2)
                            },
            label = { Text(text = "wiliboxas2") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )

        TextField(value = signalsState.sensor3,
            onValueChange = {
                viewModel.updateSignalState(signalsState.copy(sensor3 = it), Field.Signal3)
                            },
            label = { Text(text = "wiliboxas3") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        Button(onClick = { /*TODO*/ }) {
        }
    }
}