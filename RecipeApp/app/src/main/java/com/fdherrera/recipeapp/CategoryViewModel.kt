package com.fdherrera.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _categoryState = mutableStateOf(CategoryState())
    val categoryState: State<CategoryState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categoryState.value = CategoryState(
                    loading = false,
                    categories = response.categories
                )
            } catch (e: Exception) {
                _categoryState.value = CategoryState(
                    loading = false,
                    error = "Error fetching categories: ${e.message}"
                )
            }
        }
    }

    data class CategoryState(
        val loading: Boolean = true,
        val categories: List<Category> = emptyList(),
        val error: String? = null,
    )
}