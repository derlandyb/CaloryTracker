package br.com.derlandybelchior.presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.derlandybelchior.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val ONE_DAY = 1L
@Composable
fun parseDateText(date: LocalDate): String {
    val today = LocalDate.now()
    return when(date ){
        today -> stringResource(id = R.string.today)
        today.minusDays(ONE_DAY) -> stringResource(id = R.string.yesterday)
        today.plusDays(ONE_DAY) -> stringResource(id = R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}