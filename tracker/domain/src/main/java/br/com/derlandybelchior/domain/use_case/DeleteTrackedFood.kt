package br.com.derlandybelchior.domain.use_case

import br.com.derlandybelchior.domain.model.TrackedFood
import br.com.derlandybelchior.domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}