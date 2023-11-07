package com.example.project.common

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project.R
import com.example.project.location.LocationListDestination
import com.example.project.map.MapDestination
import com.example.project.signal.SignalListDestination
import com.example.project.user.UserDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, menuOnClick: () -> Unit = {}) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick =  menuOnClick ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Navigation menu"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    userNavigate: () -> Unit,
    signalNavigate: () -> Unit,
    mapNavigate: () -> Unit,
    locationsNavigate: () -> Unit,
    route: String?,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    //TODO pagalvoti del drawer close

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = modifier) {
                Text(
                    text = stringResource(R.string.drawer_title),
                    modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawer_user)) },
                    selected = route == UserDestination.route,
                    onClick = userNavigate
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawer_signal)) },
                    selected = route == SignalListDestination.route,
                    onClick = signalNavigate
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawer_map)) },
                    selected = route == MapDestination.route,
                    onClick = mapNavigate
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawer_location)) },
                    selected = route == LocationListDestination.route,
                    onClick = locationsNavigate
                )
            }
        },
        content = content
    )
}

fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}