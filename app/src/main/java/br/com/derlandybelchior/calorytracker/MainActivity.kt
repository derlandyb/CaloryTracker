package br.com.derlandybelchior.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.derlandybelchior.calorytracker.ui.theme.CaloryTrackerTheme
import br.com.derlandybelchior.core.navigation.Route
import br.com.derlandybelchior.calorytracker.navigation.navigate
import br.com.derlandybelchior.presentation.gender.GenderScreen
import br.com.derlandybelchior.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.WELCOME) {
                    composable(Route.WELCOME){
                        WelcomeScreen(
                            onNavigate = navController::navigate
                        )
                    }
                    composable(Route.AGE){}
                    composable(Route.GENDER){
                        GenderScreen(onNavigate = navController::navigate)
                    }
                    composable(Route.HEIGHT){}
                    composable(Route.WEIGHT){}
                    composable(Route.NUTRIENT_GOAL){}
                    composable(Route.ACTIVITY){}
                    composable(Route.GOAL){}
                    composable(Route.TRACKER_OVERVIEW){}
                    composable(Route.SEARCH){}
                }
            }
        }
    }
}
