package br.com.derlandybelchior.domain.di

import br.com.derlandybelchior.core.domain.preferences.Preferences
import br.com.derlandybelchior.domain.repository.TrackerRepository
import br.com.derlandybelchior.domain.use_case.CalculateMealNutrients
import br.com.derlandybelchior.domain.use_case.DeleteTrackedFood
import br.com.derlandybelchior.domain.use_case.GetFoodsForDate
import br.com.derlandybelchior.domain.use_case.SearchFood
import br.com.derlandybelchior.domain.use_case.TrackFood
import br.com.derlandybelchior.domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun providesTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ) = TrackerUseCases (
        trackFood = TrackFood(repository),
        searchFood = SearchFood(repository),
        getFoodsForDate = GetFoodsForDate(repository),
        deleteTrackedFood = DeleteTrackedFood(repository),
        calculateMealNutrients = CalculateMealNutrients(preferences)
    )
}