package com.fdherrera.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    categoryName: String,
    onNavigateToCategories: () -> Unit
) {
    val viewModel = viewModel<MealViewModel>()
    viewModel.fetchMeals(categoryName)
    val mealsViewState by viewModel.mealsState
    Column {
        Button(onClick = onNavigateToCategories) {
            Text("Back to Categories")
        }
        when {
            mealsViewState.loading -> CircularProgressIndicator(modifier.align(Alignment.CenterHorizontally))
            mealsViewState.error != null -> Text("Error occurred: ${mealsViewState.error}")
            else -> MealsScreen(mealsViewState.meals)
        }
    }
}

@Composable
fun MealsScreen(meals: List<Meal>) {
    LazyVerticalGrid(
        GridCells.Fixed(1),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals) { meal -> MealItem(meal) }
    }
}

@Composable
fun MealItem(meal: Meal) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(meal.imageUrl),
            contentDescription = meal.name,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = meal.name,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
