package br.com.derlandybelchior.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.derlandybelchior.calorytracker.navigation.navigate
import br.com.derlandybelchior.calorytracker.ui.theme.CaloryTrackerTheme
import br.com.derlandybelchior.core.navigation.Route
import br.com.derlandybelchior.presentation.activity.ActivityScreen
import br.com.derlandybelchior.presentation.age.AgeScreen
import br.com.derlandybelchior.presentation.gender.GenderScreen
import br.com.derlandybelchior.presentation.goal.GoalScreen
import br.com.derlandybelchior.presentation.height.HeightScreen
import br.com.derlandybelchior.presentation.nutrient_goal.NutrientGoalScreen
import br.com.derlandybelchior.presentation.weight.WeightScreen
import br.com.derlandybelchior.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize(),
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.TRACKER_OVERVIEW) {}
                        composable(Route.SEARCH) {}
                    }
                }
            }
        }
    }
}
