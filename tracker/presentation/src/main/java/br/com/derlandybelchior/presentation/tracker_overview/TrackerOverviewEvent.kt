package br.com.derlandybelchior.presentation.tracker_overview

import br.com.derlandybelchior.domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClick: TrackerOverviewEvent()
    object OnPreviousDayClick: TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackedFoodDClick(val trackedFood: TrackedFood): TrackerOverviewEvent()
}