package com.fdherrera.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {
    private val _mealsViewState = mutableStateOf(MealState())
    val mealsState: State<MealState> = _mealsViewState

    fun fetchMeals(categoryName: String) {
        viewModelScope.launch {
            try {
                val response: MealsResponse =
                    apiService.filterMeals(mapOf(Pair("c", categoryName)))
                _mealsViewState.value = MealState(
                    loading = false,
                    meals = response.meals
                )
            } catch (e: Exception) {
                _mealsViewState.value = MealState(
                    loading = false,
                    error = "Error fetching meals: ${e.message}"
                )
            }
        }
    }
}

data class MealState(
    val loading: Boolean = true,
    val meals: List<Meal> = emptyList(),
    val error: String? = null,
)