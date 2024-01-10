package br.com.derlandybelchior.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.derlandybelchior.calorytracker.ui.theme.CaloryTrackerTheme
import br.com.derlandybelchior.core.domain.preferences.Preferences
import br.com.derlandybelchior.calorytracker.navigation.Route
import br.com.derlandybelchior.presentation.activity.ActivityScreen
import br.com.derlandybelchior.presentation.age.AgeScreen
import br.com.derlandybelchior.presentation.gender.GenderScreen
import br.com.derlandybelchior.presentation.goal.GoalScreen
import br.com.derlandybelchior.presentation.height.HeightScreen
import br.com.derlandybelchior.presentation.nutrient_goal.NutrientGoalScreen
import br.com.derlandybelchior.presentation.search.SearchScreen
import br.com.derlandybelchior.presentation.tracker_overview.TrackerOverviewScreen
import br.com.derlandybelchior.presentation.weight.WeightScreen
import br.com.derlandybelchior.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
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
                        startDestination = if (shouldShowOnboarding) Route.WELCOME else Route.TRACKER_OVERVIEW,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNextClick = {
                                    navController.navigate(Route.GENDER)
                                }
                            )
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.HEIGHT)
                                }
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.AGE)
                            })
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                }
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ACTIVITY)
                                }
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNavigate = {
                                    navController.navigate(Route.GOAL)
                                }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                }
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNextClick = {
                                navController.navigate(Route.NUTRIENT_GOAL)
                            })
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(onNavigateToSearch = { mealName, day, month, year ->
                                navController.navigate(
                                    Route.SEARCH + "/$mealName" +
                                        "/$day" +
                                        "/$month" +
                                        "/$year"
                                )
                            })
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}"
                                + "/{dayOfMonth}"
                                + "/{month}"
                                + "/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")
                            val month = it.arguments?.getInt("month")
                            val year = it.arguments?.getInt("year")

                            if (mealName != null && dayOfMonth != null && month != null && year != null) {
                                SearchScreen(
                                    scaffoldState = scaffoldState,
                                    mealName = mealName,
                                    dayOfMonth = dayOfMonth,
                                    month = month,
                                    year = year,
                                    onNavigateUp = {
                                        navController.navigateUp()
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
