package com.example.project.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.project.R
import com.example.project.common.AppBar
import com.example.project.data.entities.Location
import com.example.project.navigation.NavigationDestination
import kotlinx.coroutines.launch

object LocationListDestination : NavigationDestination {
    override val route = "locationList"
    override val titleRes = R.string.locationList_destination
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationList(
    viewModel: LocationViewModel = hiltViewModel(),
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
            val locations by viewModel.locations.collectAsStateWithLifecycle(
                initialValue = emptyList())

            if (locations.isEmpty()) {
                Text(
                    text = "Tusčia",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp)
                )
            }
            else {
                LazyColumn()
                {
                    items(items = locations, key = { it!!.id }) { item ->
                        LocationCard(
                            item = item!!
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    item: Location
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(4.dp)){
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "x",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(0.2f),
                    textAlign = TextAlign.Center
                )
                Text(text = "y",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(0.25f),
                    textAlign = TextAlign.Center
                )
                Text(text = "Astumas",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.x.toString(),
                    modifier = Modifier.fillMaxWidth(0.2f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = item.y.toString(),
                    modifier = Modifier.fillMaxWidth(0.25f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = item.distance.toString(),
                    modifier = Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}