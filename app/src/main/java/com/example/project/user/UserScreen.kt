package com.example.project.user

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)

// TODO handlint kai dar nera mac adreso - ar tai rasyti kad nera pasetintas ar kazkas panasaus
// TODO limit length
// TODO GAL REIKTU TOAST pranesimo ar pavyko ikelt nzn
// TODO galeciau auto prideti dvitaski kad 2 simbolius, liminti length ir t.t
// TODO viskas gerai veikia, bet tik kaip noresiu prideti nauja signala, neleis, nes nera uzdeto mac adreso
fun UserScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    val defaultValue = "XX:XX:XX:XX:XX:XX:XX"

    val context = LocalContext.current

    // TODO PUT TO THE VIEWMODEL SO THAT CONFIGURATION CHANGES DOES NOT CHANGE VALUES
    var currentValue by remember { mutableStateOf(defaultValue) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Vartotojo MAC adresas",
            style = MaterialTheme.typography.titleLarge
        )
        TextField(
            value = currentValue,
            singleLine = true,
            onValueChange = {currentValue = it},
            trailingIcon = {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    toast(context, "MAC adresas pridetas")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Edit MAC address"
                    )
                }
            }
        )
    }
}

fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

