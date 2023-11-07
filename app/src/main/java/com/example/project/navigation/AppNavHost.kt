package com.example.project.navigation

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.project.common.Drawer
import com.example.project.location.LocationList
import com.example.project.location.LocationListDestination
import com.example.project.map.Map
import com.example.project.map.MapDestination
import com.example.project.signal.AddSignalDestination
import com.example.project.signal.SignalForm
import com.example.project.signal.SignalList
import com.example.project.signal.SignalListDestination
import com.example.project.signal.SignalUpdateForm
import com.example.project.signal.UpdateSignalDestination
import com.example.project.user.UserDestination
import com.example.project.user.UserScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentBackStack by navController.currentBackStackEntryAsState()

    Drawer(
        modifier = Modifier.width(300.dp),
        userNavigate = { navController.navigate(route = UserDestination.route) },
        signalNavigate = { navController.navigate(route = SignalListDestination.route) },
        mapNavigate = { navController.navigate(route = MapDestination.route) },
        locationsNavigate = { navController.navigate(route = LocationListDestination.route) },
        route = currentBackStack?.destination?.route,
        drawerState = drawerState
    ) {
        //todo nevisada leisti grizti, pvz po formos ikelimo, neleisti back i forma
        NavHost(navController = navController, startDestination = UserDestination.route) {
            composable(route = UserDestination.route) {
                UserScreen(
                    drawerState = drawerState,
                    titleRes = UserDestination.titleRes
                )
            }
            composable(route = SignalListDestination.route) {
                SignalList(
                    drawerState = drawerState,
                    titleRes = SignalListDestination.titleRes,
                    addSignalNavigate = { navController.navigate(route = AddSignalDestination.route) },
                    updateNavigate = { navController.navigate(
                        route = "${UpdateSignalDestination.route}/$it") }
                )
            }
            composable(route = AddSignalDestination.route) {
                SignalForm(
                    navigateBack = { navController.popBackStack() },
                    drawerState = drawerState,
                    titleRes = AddSignalDestination.titleRes
                )
            }
            composable(
                route = UpdateSignalDestination.routeWithArgs,
                arguments = listOf(navArgument(UpdateSignalDestination.itemIdArg) {
                    type = NavType.IntType
                })
            ) {
                SignalUpdateForm(
                    navigateBack = { navController.popBackStack() },
                    drawerState = drawerState,
                    titleRes = UpdateSignalDestination.titleRes,
                    signalId = it.arguments?.getInt(UpdateSignalDestination.itemIdArg)?: 0 //todo handle if 0
                )
            }
            composable(route = MapDestination.route) {
                Map(
                    drawerState = drawerState,
                    titleRes = MapDestination.titleRes
                )
            }
            composable(route = LocationListDestination.route) {
                LocationList(
                    drawerState = drawerState,
                    titleRes = LocationListDestination.titleRes
                )
            }
        }
    }
}