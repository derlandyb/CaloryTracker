package br.com.derlandybelchior.domain.use_case

import br.com.derlandybelchior.domain.model.TrackedFood
import br.com.derlandybelchior.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(localDate = date)
    }
}