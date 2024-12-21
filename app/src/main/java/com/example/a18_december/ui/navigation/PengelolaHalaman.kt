package com.example.a18_december.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a18_december.view.dosen.DestinasiInsert
import com.example.a18_december.view.dosen.HomeDsnView
import com.example.a18_december.view.dosen.InsertDsnView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = AlamatNavigasi.DestinasiHome.route) {
        composable(
            route = AlamatNavigasi.DestinasiHome.route
        ){
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsert.route
        ){
            InsertDsnView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}