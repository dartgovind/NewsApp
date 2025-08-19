package com.example.anew.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anew.prasentation.view.screen.HomeScreen
import com.example.anew.prasentation.view.screen.UserScreen
import com.example.anew.prasentation.view.screen.VideoItem
import com.example.anew.prasentation.viewmodel.NewsViewModel

@Composable
fun Navigation(viewModel: NewsViewModel){
    val navHostController= rememberNavController()
    NavHost(navController = navHostController, startDestination = Routes.HomeScreen) {
        composable<Routes.HomeScreen> {
            HomeScreen(viewModel,navHostController)
        }
        composable<Routes.Video> {
            VideoItem(navHostController)
        }
        composable<Routes.User> {
            UserScreen(navHostController)
        }
    }

    }
