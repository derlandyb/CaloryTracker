package br.com.derlandybelchior.calorytracker

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.derlandybelchior.calorytracker.navigation.Route
import br.com.derlandybelchior.calorytracker.ui.theme.CaloryTrackerTheme
import br.com.derlandybelchior.core.R
import br.com.derlandybelchior.presentation.search.SearchScreen
import br.com.derlandybelchior.presentation.search.SearchViewModel
import br.com.derlandybelchior.presentation.tracker_overview.TrackerOverviewScreen
import br.com.derlandybelchior.presentation.tracker_overview.TrackerOverviewViewModel
import com.google.common.truth.Truth

internal fun trackerOverviewScreenRobot(
    composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    func: TrackerOverViewE2ERobot.() -> Unit
) = TrackerOverViewE2ERobot(composeRule).also(func)
internal class TrackerOverViewE2ERobot(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {

    private lateinit var navController: NavHostController
    fun openTrackerOverviewScreen(
        trackedOverviewViewModel: TrackerOverviewViewModel,
        searchViewModel: SearchViewModel
    ) {
        composeRule.activity.setContent {

            CaloryTrackerTheme {
                val scaffoldState = rememberScaffoldState()
                navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize(),
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.TRACKER_OVERVIEW,
                        modifier = Modifier.padding(paddingValues)
                    ) {

                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" + "/$day" + "/$month" + "/$year"
                                    )
                                },
                                viewModel = trackedOverviewViewModel,
                            )
                        }
                        composable(route = Route.SEARCH + "/{mealName}" + "/{dayOfMonth}" + "/{month}" + "/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                type = NavType.StringType
                            }, navArgument("dayOfMonth") {
                                type = NavType.IntType
                            }, navArgument("month") {
                                type = NavType.IntType
                            }, navArgument("year") {
                                type = NavType.IntType
                            },)
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
                                    viewModel = searchViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun verifyAddBreakfastButtonIsHidden() = composeRule.onNodeWithText("Add Breakfast").assertDoesNotExist()

    fun clickOnBreakfastToAddItems() = composeRule.onNodeWithContentDescription("Breakfast").performClick()

    fun verifyAddBreakfastButtonIsShown() = composeRule.onNodeWithText("Add Breakfast").assertIsDisplayed()

    fun clickOnAddBreakfastButton() = composeRule.onNodeWithText("Add Breakfast").performClick()

    fun verifySearchScreenIsOpen() {
        Truth.assertThat(
            navController.currentDestination?.route?.startsWith(Route.SEARCH)
        ).isTrue()
    }

    fun verifySearchTextFieldIsDisplayed() = composeRule.onNodeWithTag("search_text_field").assertIsDisplayed()

    fun addTextToSearch(term: String) = composeRule.onNodeWithTag("search_text_field")
        .performTextInput(term)

    fun clickOnSearchIcon() = composeRule.onNodeWithContentDescription("Search...").performClick()

    fun clickOnCarbsToAddAmount() = composeRule.onNodeWithText("Carbs").performClick()

    fun addAmount(amount: String) = composeRule.onNodeWithContentDescription("Amount")
        .performTextInput(amount)

    fun clickOnTrackButtonToAddItem() = composeRule
        .onNodeWithContentDescription("Track")
        .performClick()

    fun verifyTrackOverviewScreenIsDisplayed() {
        Truth.assertThat(
            navController.currentDestination?.route?.startsWith(Route.TRACKER_OVERVIEW)
        ).isTrue()
    }

    fun assertCorrectsValuesIsDisplayed(expectedValue: String) {
        composeRule.onAllNodesWithText(expectedValue).onFirst()
            .assertIsDisplayed()
    }

    fun verifyErrorMessageIsDisplayed() {
        composeRule.onAllNodesWithText(
            composeRule.activity.getString(R.string.error_something_went_wrong)
        )
            .onFirst()
            .assertIsDisplayed()
    }
}