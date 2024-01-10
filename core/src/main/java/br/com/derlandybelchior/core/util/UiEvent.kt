package br.com.derlandybelchior.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    data class ShowMessage(val message: UiText): UiEvent()
}