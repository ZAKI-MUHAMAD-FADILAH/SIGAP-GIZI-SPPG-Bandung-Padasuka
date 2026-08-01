package id.sppg.padasuka.sigapgizi.app

sealed class AppDestination(val route: String) {
    data object Login : AppDestination("login")

    data object Locations : AppDestination("locations")

    data object Inactive : AppDestination("inactive")

    data object Error : AppDestination("error")
}
