package com.example.project.signal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.project.R
import com.example.project.common.AppBar
import com.example.project.common.toast
import com.example.project.data.entities.Signal
import com.example.project.navigation.NavigationDestination
import kotlinx.coroutines.launch

object SignalListDestination : NavigationDestination {
    override val route = "signalList"
    override val titleRes = R.string.signalList_destination
}

object AddSignalDestination : NavigationDestination {
    override val route = "addSignal"
    override val titleRes = R.string.addSignal_destination
}

object UpdateSignalDestination : NavigationDestination {
    override val route = "updateSignal"
    override val titleRes = R.string.updateSignal_destination
    const val itemIdArg = "signalId"
    val routeWithArgs = "${UpdateSignalDestination.route}/{${UpdateSignalDestination.itemIdArg}}"
}

enum class Field {
    Signal1, Signal2, Signal3
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalForm(
    viewModel: SignalEntryViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    drawerState: DrawerState,
    titleRes: Int
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(titleRes),
                menuOnClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            val signalsState: Signal = viewModel.signalState
            val validationState = viewModel.validationState
            val focusManager = LocalFocusManager.current
            val context = LocalContext.current
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                TextField(
                    value = if (signalsState.sensor1 != Int.MIN_VALUE)
                        signalsState.sensor1.toString() else "",
                    onValueChange = {
                        viewModel.updateSignalState(it, Field.Signal1)
                    },
                    label = { Text(text = "wiliboxas1") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    isError = validationState.contains(Field.Signal1)
                )
                TextField(
                    value = if (signalsState.sensor2 != Int.MIN_VALUE)
                        signalsState.sensor2.toString() else "",
                    onValueChange = {
                        viewModel.updateSignalState(it, Field.Signal2)
                    },
                    label = { Text(text = "wiliboxas2") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    isError = validationState.contains(Field.Signal2)
                )

                TextField(
                    value = if (signalsState.sensor3 != Int.MIN_VALUE)
                        signalsState.sensor3.toString() else "",
                    onValueChange = {
                        viewModel.updateSignalState(it, Field.Signal3)
                    },
                    label = { Text(text = "wiliboxas3") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    isError = validationState.contains(Field.Signal3)
                )
                Button(onClick = {
                    viewModel.insertSignal()
                    focusManager.clearFocus()
                    if (validationState.isEmpty()) {
                        toast(
                            context,
                            text = context.resources.getString(R.string.signal_addedNotification)
                        )
                        navigateBack()
                    }
                }) {
                    Text(text = "Pridėti")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalList(
    viewModel: SignalViewModel = hiltViewModel(),
    drawerState: DrawerState,
    titleRes: Int,
    addSignalNavigate: () -> Unit,
    updateNavigate: (id: Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(titleRes),
                menuOnClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = addSignalNavigate) {
                Icon(Icons.Filled.Add, "Add new signal button")
            }
        }
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val signals by viewModel.signals.collectAsStateWithLifecycle(initialValue = emptyList())

            if (signals.isEmpty()) {
                Text(
                    text = "Tusčia",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp)
                )}
            else {
                LazyColumn()
                {
                    items(items = signals, key = { it!!.id }) { item ->
                        SignalCard(
                            item = item!!,
                            deleteSignal = { viewModel.deleteSignal(item!!) },
                            updateNavigate = { updateNavigate(item!!.id) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalCard(
    modifier: Modifier = Modifier,
    item: Signal,
    deleteSignal: () -> Unit,
    updateNavigate: () -> Unit
    ) {
    Card(
        onClick = updateNavigate,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(4.dp)){
            Row(
                modifier = Modifier.fillMaxWidth(),
                //verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "MAC adresas",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(0.5f),
                    textAlign = TextAlign.Center
                )
                Text(text = "Stiprumai",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.macAddress,
                    modifier = Modifier.fillMaxWidth(0.5f),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "1. ${item.sensor1}")
                    Text(text = "2. ${item.sensor2}")
                    Text(text = "3. ${item.sensor3}")
                }
                IconButton(onClick = deleteSignal, modifier = Modifier.fillMaxWidth(1f)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Signal delete button"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalUpdateForm(
    viewModel: SignalUpdateViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    drawerState: DrawerState,
    titleRes: Int,
    signalId: Int
) {
    val scope = rememberCoroutineScope()
    viewModel.getSignal(signalId)

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(titleRes),
                menuOnClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {

            val signalsState: Signal = viewModel.signalState
            val validationState = viewModel.validationState
            val focusManager = LocalFocusManager.current
            val context = LocalContext.current
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {

                TextField(
                    value = if (signalsState.sensor1 != Int.MIN_VALUE)
                        signalsState.sensor1.toString() else "",
                    onValueChange = {
                        viewModel.updateSignalState(it, Field.Signal1)
                    },
                    label = { Text(text = "wiliboxas1") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    isError = validationState.contains(Field.Signal1)
                )

                TextField(
                    value = if (signalsState.sensor2 != Int.MIN_VALUE)
                        signalsState.sensor2.toString() else "",
                    onValueChange = {
                        viewModel.updateSignalState(it, Field.Signal2)
                    },
                    label = { Text(text = "wiliboxas2") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    isError = validationState.contains(Field.Signal2)
                )

                TextField(
                    value = if (signalsState.sensor3 != Int.MIN_VALUE)
                        signalsState.sensor3.toString() else "",
                    onValueChange = {
                        viewModel.updateSignalState(it, Field.Signal3)
                    },
                    label = { Text(text = "wiliboxas3") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    isError = validationState.contains(Field.Signal3)
                )
                Button(onClick = {
                    viewModel.updateSignal()
                    focusManager.clearFocus()
                    if (validationState.isEmpty()) {
                        toast(
                            context,
                            text = context.resources.getString(R.string.signal_updatedNotification)
                        )
                        navigateBack()
                    }
                }) {
                    Text(text = "Redaguoti")
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewCard()
{
    val signal:Signal = Signal(0, "XX-XX-XX-XX-XX-XX", 123, 333, 555)
    SignalCard(item = signal, deleteSignal = {}, updateNavigate = {})
}
