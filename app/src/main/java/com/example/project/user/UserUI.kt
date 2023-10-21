package com.example.project.user

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.data.entities.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview(showBackground = true)

// TODO handlint kai dar nera mac adreso - ar tai rasyti kad nera pasetintas ar kazkas panasaus
// TODO limit length
// TODO GAL REIKTU TOAST pranesimo ar pavyko ikelt nzn
// TODO galeciau auto prideti dvitaski kad 2 simbolius, liminti length ir t.t
// TODO viskas gerai veikia, bet tik kaip noresiu prideti nauja signala, neleis, nes nera uzdeto mac adreso
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = viewModel()/*TODO paskui pakeisti su hiltViewMode(), kai turesiu navigation graph*/
) {
    val focusManager = LocalFocusManager.current
    //TODO ir reiktu tada navigatint atgal? nzn
    val context = LocalContext.current

    val userState: User = viewModel.userState

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Vartotojo MAC adresas",
            style = MaterialTheme.typography.titleLarge
        )
        TextField(
            value = userState.macAddress,
            singleLine = true,
//            enabled = userState != null,
            onValueChange = { viewModel.updateMACAddressState(it) },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.updateUser(userState)
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

//TODO maybe move somewhere
fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

