package br.com.derlandybelchior.presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.derlandybelchior.core.R
import br.com.derlandybelchior.core.domain.preferences.Preferences
import br.com.derlandybelchior.core.util.UiEvent
import br.com.derlandybelchior.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightScreenViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var weight by mutableStateOf("0.0")
    private set

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            this.weight = weight
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: run {
                val resIdMessage = if (weight.isEmpty()) {
                    R.string.error_weight_cant_be_empty
                } else {
                    R.string.error_weight_must_be_decimal_number
                }
                _uiEvent.send(
                    UiEvent.ShowMessage(
                        UiText.StringResource(resIdMessage)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}