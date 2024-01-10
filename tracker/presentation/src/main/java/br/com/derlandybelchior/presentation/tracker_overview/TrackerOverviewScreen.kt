package br.com.derlandybelchior.presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.derlandybelchior.core.R
import br.com.derlandybelchior.core_ui.LocalSpacing
import br.com.derlandybelchior.presentation.tracker_overview.components.AddButton
import br.com.derlandybelchior.presentation.tracker_overview.components.DaySelector
import br.com.derlandybelchior.presentation.tracker_overview.components.ExpandableMeal
import br.com.derlandybelchior.presentation.tracker_overview.components.NutrientsHeader
import br.com.derlandybelchior.presentation.tracker_overview.components.TrackedFoodItem

@Composable
fun TrackerOverviewScreen(
    onNavigateToSearch: (String, Int, Int, Int) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            DaySelector(date = state.date,
                onPreviousDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal, onToggleClick = {
                    viewModel.onEvent(
                        TrackerOverviewEvent.OnToggleMealClick(meal)
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceSmall)
                ) {
                    state.trackedFoods.forEach { food ->
                        TrackedFoodItem(
                            trackedFood = food,
                            onDeleteClick = {
                                viewModel.onEvent(
                                    TrackerOverviewEvent.OnDeleteTrackedFoodDClick(
                                        food
                                    )
                                )
                            },
                        )
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    }
                    AddButton(
                        text = stringResource(
                            id = R.string.add_meal, meal.name.asString(context)
                        ),
                        onClick = {
                            onNavigateToSearch(
                                meal.name.asString(context),
                                state.date.dayOfMonth,
                                state.date.monthValue,
                                state.date.year
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}