package com.example.anew.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreen : Routes()
    @Serializable
    data object  Video: Routes()
    @Serializable
    data object User: Routes()

}