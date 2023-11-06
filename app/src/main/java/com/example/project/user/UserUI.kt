package com.example.project.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.project.R
import com.example.project.common.AppBar
import com.example.project.common.toast
import com.example.project.data.entities.User
import com.example.project.navigation.NavigationDestination
import kotlinx.coroutines.launch

object UserDestination : NavigationDestination {
    override val route = "user"
    override val titleRes = R.string.user_destination
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

// TODO handlint kai dar nera mac adreso - ar tai rasyti kad nera pasetintas ar kazkas panasaus
// TODO viskas gerai veikia, bet tik kaip noresiu prideti nauja signala, neleis, nes nera uzdeto mac adreso
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel(),
    drawerState: DrawerState,
    titleRes: Int
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { AppBar(
            title = stringResource(titleRes),
            menuOnClick = {
                scope.launch {
                    drawerState.apply { if (isClosed) open() else close() }
                }
            }
        ) }
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {

            val focusManager = LocalFocusManager.current
            val context = LocalContext.current
            val userState: User = viewModel.userState

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                TextField(
                    value = userState.macAddress,
                    singleLine = true,
                    onValueChange = { viewModel.updateMACAddressState(it) },
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.updateUser(userState)
                            focusManager.clearFocus()
                            toast(
                                context,
                                text = context.resources.getString(R.string.user_addedNotification)
                            )
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
    }
}