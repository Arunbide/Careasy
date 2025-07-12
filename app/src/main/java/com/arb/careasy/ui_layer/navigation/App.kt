package com.arb.careasy.ui_layer.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arb.careasy.ui_layer.CarDescriptionScreen
import com.arb.careasy.ui_layer.HomeScreen
import com.arb.careasy.ui_layer.IntroScreenUI

@Composable
fun App(){
val navController= rememberNavController()
    NavHost(navController= navController, startDestination = IntroScreen) {

        composable<IntroScreen>{
            IntroScreenUI(navController = navController)
        }
        composable<Homescreen>{
            HomeScreen(navController = navController)
        }
        composable <descriptionscreen>{
            CarDescriptionScreen(navController=navController)
        }
    }
}