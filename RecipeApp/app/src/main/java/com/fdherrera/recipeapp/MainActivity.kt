package com.fdherrera.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fdherrera.recipeapp.ui.theme.RecipeAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppTheme {
                Surface() {
                    NavigationController()
                }
            }
        }
    }
}

@Serializable
object Categories

@Serializable
data class CategoryNav(val categoryName: String)

@Composable
fun NavigationController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Categories) {
        composable<Categories> {
            CategoriesScreen(
                onNavigateToCategory = { categoryName ->
                    navController.navigate(CategoryNav(categoryName))
                }
            )
        }
        composable<CategoryNav> {
            val categoryNav: CategoryNav = it.toRoute()
            MealsScreen(categoryName = categoryNav.categoryName)
        }
    }
}