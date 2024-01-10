package br.com.derlandybelchior.data.repository

import br.com.derlandybelchior.data.local.dao.TrackerDao
import br.com.derlandybelchior.data.mapper.toTrackableFood
import br.com.derlandybelchior.data.mapper.toTrackedFood
import br.com.derlandybelchior.data.mapper.toTrackedFoodEntity
import br.com.derlandybelchior.data.remote.OpenFoodApi
import br.com.derlandybelchior.domain.model.TrackableFood
import br.com.derlandybelchior.domain.model.TrackedFood
import br.com.derlandybelchior.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl (
    private val dao: TrackerDao,
    private val api: OpenFoodApi): TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )

            val trackableFoods = searchDto.products
                .filter {
                    val calculatedCalories = it.nutriments.carbohydrates100g * 4f +
                        it.nutriments.proteins100g * 4f +
                        it.nutriments.fat100g * 9f

                    val lowerBound = calculatedCalories * 0.99f
                    val upperBound = calculatedCalories * 1.01f
                    it.nutriments.energyKcal100g in (lowerBound..upperBound)
                }
                .mapNotNull {
                it.toTrackableFood()
            }

            Result.success(trackableFoods)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map {  entities ->
            entities.map { it.toTrackedFood() }
        }
    }

}