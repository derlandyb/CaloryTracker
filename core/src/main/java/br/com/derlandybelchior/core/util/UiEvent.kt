package br.com.derlandybelchior.core.util

sealed class UiEvent {
    data class Navigate(val route: String): UiEvent()
    object NavigateUp: UiEvent()
    data class ShowMessage(val message: UiText): UiEvent()
}