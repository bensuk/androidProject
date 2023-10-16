package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.common.AppBar
import com.example.project.ui.theme.ProjectTheme
import com.example.project.user.UserScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme() {

                Drawer()





            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProjectPreview() {
    ProjectTheme {
        Scaffold(
            topBar = { AppBar("AppBar Title") },
        ) { innerPadding ->
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column {
                    repeat(20) { index ->
                        Text(text = "testuoju: $index")
                    }
                }
            }
        }
    }
}

// TODO ekranų stiprumų žemėlapyje, gali pridėti floating actin button, kuris leistų pridėti naują signalą
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // TODO pakeisti tiek stringus ir path kad nebutu ihardcodinti
            ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                Text("Navigacija", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Vartotojo identifikacija") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Signalų stiprumų sąrašas") },
                    selected = true,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Signalų pridėjimas") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Signalų žemėlapis") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }
        }
    ) {
        val scope = rememberCoroutineScope()
        Scaffold(
            topBar = { AppBar(
                title = "AppBar Title",
                menuOnClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }
            ) }
        ) { innerPadding ->
            Surface(color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                UserScreen(modifier = Modifier.padding(top = 40.dp))
            }
        }
    }
}





//@Composable
//fun CraneHome(
//    onExploreItemClicked: OnExploreItemClicked,
//    modifier: Modifier = Modifier,
//) {
//    val scaffoldState = rememberScaffoldState()
//    Scaffold(
//        scaffoldState = scaffoldState,
//        modifier = Modifier.statusBarsPadding(),
//        drawerContent = {
//            CraneDrawer()
//        }
//    ) { padding ->
//        val scope = rememberCoroutineScope()
//        CraneHomeContent(
//            modifier = modifier.padding(padding),
//            onExploreItemClicked = onExploreItemClicked,
//            openDrawer = {
//                scope.launch {
//                    scaffoldState.drawerState.open()
//                }
//            }
//        )
//    }
//}
