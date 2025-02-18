package com.example.navik.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navik.screens.Dobro
import com.example.navik.screens.ForgotPassword
import com.example.navik.screens.SignIn
import com.example.navik.screens.SignUp
import com.example.navik.screens.SplashScreen

@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Splashscreen"){
        composable("SplashScreen"){
            SplashScreen(navController = navController)

        }

        composable("SignIn"){
            SignIn(
                onSignInSuccess = { navController.navigate("Dobro"){
                    popUpTo("SignIn") {inclusive = true}
                } },
                onSignUpClick = { navController.navigate("SignUp") },
                onForgotPassword = { navController.navigate("ForgotPassword") },
                navController = navController)
        }

        composable("SignUp"){
            SignUp(
                onSignUpSuccess = { navController.navigate("SignIn") {
                    popUpTo("SignUp"){inclusive = true}
                } },
                navController = navController)
        }

        composable("ForgotPassword"){
            ForgotPassword(
                onForgotSignIn = { navController.navigate("SignIn") {
                    popUpTo("ForgotPassword") {inclusive = true}
                } },
                navController = navController)
        }

        composable("Dobro"){
            Dobro(
                navController = navController)
        }

    }

}


