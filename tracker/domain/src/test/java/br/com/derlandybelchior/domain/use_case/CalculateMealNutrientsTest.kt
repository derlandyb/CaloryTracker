package br.com.derlandybelchior.domain.use_case

import br.com.derlandybelchior.core.domain.model.ActivityLevel
import br.com.derlandybelchior.core.domain.model.Gender
import br.com.derlandybelchior.core.domain.model.GoalType
import br.com.derlandybelchior.core.domain.model.UserInfo
import br.com.derlandybelchior.core.domain.preferences.Preferences
import br.com.derlandybelchior.domain.model.MealType
import br.com.derlandybelchior.domain.model.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp() {
        val preferences = mockk<Preferences>(relaxed = true)
        every {
            preferences.loadUserInfo()
        } returns UserInfo(
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
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = listOf(
                    MealType.Breakfast,
                    MealType.Lunch,
                    MealType.Dinner,
                    MealType.Snack,
                ).random(),
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000),
            )
        }
        val result = calculateMealNutrients(trackedFoods)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }
        val breakfastExpectedCalories = trackedFoods
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(breakfastExpectedCalories)
    }

    @Test
    fun `Carbs for dinner properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = listOf(
                    MealType.Breakfast,
                    MealType.Lunch,
                    MealType.Dinner,
                    MealType.Snack,
                ).random(),
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000),
            )
        }
        val result = calculateMealNutrients(trackedFoods)

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }
        val dinnerExpectedCarbs = trackedFoods
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(dinnerExpectedCarbs)
    }
}