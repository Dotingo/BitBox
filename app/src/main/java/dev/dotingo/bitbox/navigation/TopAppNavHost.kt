package dev.dotingo.bitbox.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dotingo.bitbox.presentation.LoginScreen
import dev.dotingo.bitbox.presentation.RegisterScreen
import dev.dotingo.bitbox.presentation.StorageScreen

@Composable
fun TopAppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Any,
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<LoginScreenNav> {
                LoginScreen(
                    navigateToMainScreen = {
                        navController.navigate(StorageScreenNav) {
                            popUpTo(LoginScreenNav) {inclusive = true}
                        }
                    },
                    navigateToRegisterScreen = {
                        navController.navigate(RegistrationScreenNav)
                    }
                )
            }
            composable<RegistrationScreenNav> {
                RegisterScreen(
                    navigateToMainScreen = {
                        navController.navigate(StorageScreenNav){
                            popUpTo(LoginScreenNav) {inclusive = true}
                        }
                    },
                    navigateToLoginScreen = {
                        navController.navigate(LoginScreenNav)
                    }
                )
            }
            composable<StorageScreenNav> {
                StorageScreen()
            }
        }
    }
}


fun navigateBack(navController: NavHostController) {
    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED
    ) {
        navController.popBackStack()
    }
}