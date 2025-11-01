package com.elflin.recipesapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elflin.recipesapp.data.container.MealDBContainer
import com.elflin.recipesapp.ui.model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel: ViewModel() {

    private val _meal = MutableStateFlow<Meal?>(null)
    val meal: StateFlow<Meal?> = _meal

    private val TAG = "RecipeDetailViewModel"

    fun loadData(id: Int){

        viewModelScope.launch {
            try {
                _meal.value = MealDBContainer().retrofitRepository.DataForRecipeDetailView(id)
            } catch (e: Exception) {
                Log.e(TAG, "loadData: Error fetching recipes ${e.message}", e)
            }
        }
    }

}