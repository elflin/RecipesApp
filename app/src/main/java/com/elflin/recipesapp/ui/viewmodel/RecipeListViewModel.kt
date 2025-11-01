package com.elflin.recipesapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elflin.recipesapp.data.container.MealDBContainer
import com.elflin.recipesapp.ui.model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    private val _allRecipes = MutableStateFlow<List<Meal>>(emptyList())
    val allRecipes: StateFlow<List<Meal>> = _allRecipes

    private val TAG = "RecipeListViewModel"

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            try {
                _allRecipes.value = MealDBContainer().retrofitRepository.DataForRecipeListView().toList()
            } catch (e: Exception) {
                Log.e(TAG, "loadRecipes: Error fetching recipes ${e.message}", e)
            }
        }
    }
}