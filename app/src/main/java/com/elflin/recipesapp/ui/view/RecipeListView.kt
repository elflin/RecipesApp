package com.elflin.recipesapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elflin.recipesapp.ui.model.Meal

@Composable
fun RecipeListView(
    modifier: Modifier = Modifier,
    allRecipes: List<Meal>
) {

    var searchText by remember { mutableStateOf("") }

    val filteredRecipes = allRecipes.filter { meal ->
        meal.meal.contains(searchText, ignoreCase = true) || meal.category.contains(
            searchText,
            ignoreCase = true
        ) || meal.area.contains(searchText, ignoreCase = true)
    }

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Cari Resep (mis. Chicken, French)") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search, contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        if (filteredRecipes.isEmpty() && searchText.isNotEmpty()) {
            Text(
                text = "Tidak ada resep yang ditemukan untuk \"$searchText\".",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(filteredRecipes) { meal ->
                    MealCard(meal = meal)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListPreview(
    modifier: Modifier = Modifier
) {
    val sampleMeal1 = Meal(
        meal = "Ratatouille",
        category = "Vegetarian",
        area = "French",
        instructions = "Instruksi singkat Ratatouille...",
        id = 52882,
        mealThumb = "https://www.themealdb.com/images/media/meals/wrpwuu1511786491.jpg",
        linkYoutube = "https://www.youtube.com/watch?v=BFdQUgAFtGU"
    )
    val sampleMeal2 = sampleMeal1.copy(
        meal = "Chicken Fajita",
        category = "Chicken",
        area = "Mexican",
        instructions = "Instruksi singkat Chicken Fajita...",
    )
    val sampleMeal3 = sampleMeal1.copy(
        meal = "Chicken Fajita 2",
        category = "Chicken",
        area = "Mexican",
        instructions = "Instruksi singkat Chicken Fajita...",
    )
    val sampleMeal4 = sampleMeal1.copy(
        meal = "Chicken Fajita 3",
        category = "Chicken",
        area = "Mexican",
        instructions = "Instruksi singkat Chicken Fajita...",
    )

    val dummyRecipes = listOf(
        sampleMeal1,
        sampleMeal2,
        sampleMeal3,
        sampleMeal4
    )

    RecipeListView(allRecipes = dummyRecipes, modifier = modifier)
}