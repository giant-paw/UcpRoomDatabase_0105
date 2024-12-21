package com.example.a18_december.ui.navigation

interface AlamatNavigasi {
    val route : String

    object DestinasiHomeDosen : AlamatNavigasi{
        override val route = "home"
    }

    object DestinasiDetailDosen : AlamatNavigasi{
        override val route = "detail"
        const val NIDN = "nidn"
        val routeWithArg = "$route/{$NIDN}"
    }
}