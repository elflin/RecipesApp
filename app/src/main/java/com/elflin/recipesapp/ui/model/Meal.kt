package com.elflin.recipesapp.ui.model

data class Meal(
    val meal: String,
    val category: String,
    val area: String,
    val instructions: String,
    val mealThumb: String,
    val id: Int,
    val linkYoutube: String
)