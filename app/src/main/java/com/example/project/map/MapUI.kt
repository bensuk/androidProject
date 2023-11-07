package com.example.project.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.project.R
import com.example.project.common.AppBar
import com.example.project.navigation.NavigationDestination
import kotlinx.coroutines.launch
import kotlin.math.abs

object MapDestination : NavigationDestination {
    override val route = "map"
    override val titleRes = R.string.map_destination
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Map(
    viewModel: MapViewModel = hiltViewModel(),
    drawerState: DrawerState,
    titleRes: Int
) {
    val scope = rememberCoroutineScope()
    val measurements by viewModel.measurements.collectAsStateWithLifecycle(
        initialValue = emptyList())

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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (measurements.isEmpty()) {
                Text(
                    text = "Tusčia",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp)
                )
            }
            else {
                val locations by viewModel.locations.collectAsStateWithLifecycle(
                    initialValue = emptyList())

                val maxY = measurements.maxOf { it.y }
                val minY = measurements.minOf { it.y }
                val maxX = measurements.maxOf { it.x }
                val minX = measurements.minOf { it.x }

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (i in minX-1 until maxX+1) {
                            Text(
                                text = if (i == minX-1) "" else i.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    val yLength = abs(minY)+abs(maxY)+1
                    val yAxis = (minY until maxY+1).toList()

                    LazyColumn {
                        items(yLength) { y->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = (yAxis[y].toString()),
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.width(28.dp)
                                )
                                for (x in minX until maxX+1) {
                                    Box(
                                        Modifier
                                            .background(
                                                if (locations.any { it.y == yAxis[y] && it.x == x })
                                                    Color.Yellow
                                                else if (measurements.any { it.y == yAxis[y] && it.x == x })
                                                    Color.Green
                                                else
                                                    Color.Unspecified
                                            )
                                            .weight(1f)
                                            .aspectRatio(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}