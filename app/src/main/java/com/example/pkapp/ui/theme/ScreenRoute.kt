package com.example.pkapp.ui.theme

sealed class ScreenRoute (val route : String){
    object PKListScreen : ScreenRoute("pklist_screen")
    object PKDetailScreen : ScreenRoute("pkdetail_screen")
    object ErrorListScreen : ScreenRoute("errorlist_screen")
    object ErrorDetailScreen : ScreenRoute("errordetail_screen")
}