package com.fdherrera.shoppinglistapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fdherrera.shoppinglistapp.ui.theme.ShoppingListAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val context = LocalContext.current
                ShoppingListAppTheme {
                    Navigation(context)
                }
            }
        }
    }
}

@Serializable
object ShoppingListScreen

@Serializable
object LocationSelectionScreen

@Composable
fun Navigation(
    context: Context
) {
    val navController = rememberNavController()
    val locationViewModel: LocationViewModel = viewModel()
    val locationUtils = LocationUtils(context)

    NavHost(navController = navController, startDestination = ShoppingListScreen) {
        composable<ShoppingListScreen> {
            ShoppingListApp(
                locationUtils,
                context,
                locationViewModel,
                onSelectingLocation = { navController.navigate(LocationSelectionScreen) })
        }
        composable<LocationSelectionScreen> {
            locationViewModel.location.value?.let {
                LocationSelectionScreen(it) { locationSelected ->
                    locationViewModel.fetchAddress(locationSelected)
                    navController.popBackStack()
                }
            }
        }
    }
}