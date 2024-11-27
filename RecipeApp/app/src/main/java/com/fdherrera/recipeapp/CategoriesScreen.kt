package com.fdherrera.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onNavigateToCategory: (String) -> Unit
) {
    val viewModel = viewModel<CategoryViewModel>()
    val categoryViewState by viewModel.categoryState
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            categoryViewState.loading -> CircularProgressIndicator(modifier.align(Alignment.Center))
            categoryViewState.error != null -> Text("Error occurred: ${categoryViewState.error}")
            else -> CategoriesScreen(
                categories = categoryViewState.categories,
                onNavigateToCategory
            )
        }

    }
}

@Composable
fun CategoriesScreen(categories: List<Category>, onNavigateToCategory: (String) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) { category -> CategoryItem(category, onNavigateToCategory) }
    }
}

@Composable
fun CategoryItem(category: Category, onNavigateToCategory: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onNavigateToCategory(category.name) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.imageUrl),
            contentDescription = category.name,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.name,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}