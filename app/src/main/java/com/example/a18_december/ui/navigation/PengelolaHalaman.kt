package com.example.a18_december.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a18_december.ui.view.dosen.DestinasiInsertDosen
import com.example.a18_december.ui.view.dosen.HomeDsnView
import com.example.a18_december.ui.view.dosen.InsertDsnView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = AlamatNavigasi.DestinasiHomeDosen.route) {
        composable(
            route = AlamatNavigasi.DestinasiHomeDosen.route
        ){
            HomeDsnView(
                onAddDsn = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertDosen.route
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