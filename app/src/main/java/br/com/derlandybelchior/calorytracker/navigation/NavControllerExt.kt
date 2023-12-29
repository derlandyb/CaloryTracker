package br.com.derlandybelchior.calorytracker.navigation

import androidx.navigation.NavController
import br.com.derlandybelchior.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}