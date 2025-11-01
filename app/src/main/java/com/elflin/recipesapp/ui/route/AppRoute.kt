package com.elflin.recipesapp.ui.route

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elflin.recipesapp.ui.view.RecipeDetailView
import com.elflin.recipesapp.ui.view.RecipeListView

enum class AppView(val title: String) {
    RecipeDetailView("Recipe Detail"),
    RecipeListView("Home")
}

@Composable
fun AppRoute() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val routeName = currentRoute?.split('/')?.first()
    val currentView = AppView.entries.find { it.name == routeName }

    Scaffold(
        topBar = {
            MyTopAppBar(
                currentView = currentView,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppView.RecipeListView.name
        ) {
            composable(route = AppView.RecipeListView.name) {
                RecipeListView(navController = navController)
            }

            composable(route = AppView.RecipeDetailView.name + "/{id}") { backStackEntry ->
                RecipeDetailView(id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentView: AppView?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Log.d("MyTopAppBar", "currentView: ${currentView?.title}")
            Text(text = currentView?.title ?: AppView.RecipeListView.title)
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}