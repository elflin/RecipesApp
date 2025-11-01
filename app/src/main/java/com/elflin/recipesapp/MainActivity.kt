package com.elflin.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.elflin.recipesapp.ui.theme.RecipesAppTheme
import com.elflin.recipesapp.ui.view.RecipeDetailPreview
import com.elflin.recipesapp.ui.view.RecipeDetailView
import com.elflin.recipesapp.ui.view.RecipeListPreview
import com.elflin.recipesapp.ui.view.RecipeListView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeListPreview(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}