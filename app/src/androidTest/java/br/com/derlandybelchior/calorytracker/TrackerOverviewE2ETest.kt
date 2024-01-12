package br.com.derlandybelchior.calorytracker

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import br.com.derlandybelchior.calorytracker.repository.TrackerRepositoryFake
import br.com.derlandybelchior.core.domain.model.ActivityLevel
import br.com.derlandybelchior.core.domain.model.Gender
import br.com.derlandybelchior.core.domain.model.GoalType
import br.com.derlandybelchior.core.domain.model.UserInfo
import br.com.derlandybelchior.core.domain.preferences.Preferences
import br.com.derlandybelchior.core.domain.use_case.FilterOutDigits
import br.com.derlandybelchior.domain.model.TrackableFood
import br.com.derlandybelchior.domain.use_case.CalculateMealNutrients
import br.com.derlandybelchior.domain.use_case.DeleteTrackedFood
import br.com.derlandybelchior.domain.use_case.GetFoodsForDate
import br.com.derlandybelchior.domain.use_case.SearchFood
import br.com.derlandybelchior.domain.use_case.TrackFood
import br.com.derlandybelchior.domain.use_case.TrackerUseCases
import br.com.derlandybelchior.presentation.search.SearchViewModel
import br.com.derlandybelchior.presentation.tracker_overview.TrackerOverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

@HiltAndroidTest
class TrackerOverviewE2ETest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryFake: TrackerRepositoryFake
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var trackedOverviewViewModel: TrackerOverviewViewModel
    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )

        repositoryFake = TrackerRepositoryFake()
        trackerUseCases = TrackerUseCases(
            trackFood = TrackFood(repositoryFake),
            searchFood = SearchFood(repositoryFake),
            getFoodsForDate = GetFoodsForDate(repositoryFake),
            deleteTrackedFood = DeleteTrackedFood(repositoryFake),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )

        trackedOverviewViewModel = TrackerOverviewViewModel(
            preferences = preferences, trackerUseCases = trackerUseCases
        )
        searchViewModel = SearchViewModel(
            filterOutDigits = FilterOutDigits(),
            trackerUseCases = trackerUseCases
        )
    }

    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated() {
        repositoryFake.searchResults = listOf(
            TrackableFood(
                name = "banana",
                imageUrl = null,
                caloriesPer100g = 150,
                carbsPer100g = 5,
                proteinsPer100g = 50,
                fatPer100g = 1
            )
        )

        val addedAmount = 150
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedCarbs = (1.5f * 50).roundToInt()
        val expectedProtein = (1.5f * 5).roundToInt()
        val expectedFat = (1.5f * 1).roundToInt()

        trackerOverviewScreenRobot(composeRule) {

            openTrackerOverviewScreen(
                trackedOverviewViewModel,
                searchViewModel
            )
            verifyAddBreakfastButtonIsHidden()
            clickOnBreakfastToAddItems()
            verifyAddBreakfastButtonIsShown()
            clickOnAddBreakfastButton()
            verifySearchScreenIsOpen()
            verifySearchTextFieldIsDisplayed()
            addTextToSearch("banana")
            clickOnSearchIcon()
            clickOnCarbsToAddAmount()
            addAmount(addedAmount.toStr())
            clickOnTrackButtonToAddItem()
            verifyTrackOverviewScreenIsDisplayed()

            assertCorrectsValuesIsDisplayed(expectedCarbs.toStr())
            assertCorrectsValuesIsDisplayed(expectedProtein.toStr())
            assertCorrectsValuesIsDisplayed(expectedFat.toStr())
            assertCorrectsValuesIsDisplayed(expectedCalories.toStr())
        }
    }

    @Test
    fun addBreakfast_onFailureEmitted_showErrorMessage () {
        repositoryFake.shouldReturnError = true

        trackerOverviewScreenRobot(composeRule) {

            openTrackerOverviewScreen(
                trackedOverviewViewModel,
                searchViewModel
            )
            verifyAddBreakfastButtonIsHidden()
            clickOnBreakfastToAddItems()
            verifyAddBreakfastButtonIsShown()
            clickOnAddBreakfastButton()
            verifySearchScreenIsOpen()
            verifySearchTextFieldIsDisplayed()
            addTextToSearch("banana")
            clickOnSearchIcon()
            verifyErrorMessageIsDisplayed()
        }
    }
}