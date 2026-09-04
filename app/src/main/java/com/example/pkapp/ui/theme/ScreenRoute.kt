package com.example.pkapp.ui.theme

sealed class ScreenRoute (val route : String){
    object PKListScreen : ScreenRoute("pklist_screen")
    object PKDetailScreen : ScreenRoute("pkdetail_screen")
    object LoadingScreen : ScreenRoute("loading_screen")
    object ErrorScreen : ScreenRoute("error_screen")
}