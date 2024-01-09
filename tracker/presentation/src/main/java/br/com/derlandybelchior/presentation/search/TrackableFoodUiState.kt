package br.com.derlandybelchior.presentation.search

import br.com.derlandybelchior.domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
