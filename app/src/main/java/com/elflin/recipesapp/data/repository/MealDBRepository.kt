package com.elflin.recipesapp.data.repository

import android.util.Log
import com.elflin.recipesapp.data.service.MealDBService
import com.elflin.recipesapp.ui.model.Meal
import java.net.HttpURLConnection

class MealDBRepository(
    private val service: MealDBService
) {
    suspend fun DataForRecipeListView(
        jumlahData: Int = 15
    ): List<Meal> {
        val returnListMeal: MutableList<Meal> = mutableListOf()

        for (i in 0 until jumlahData) {
            val dataMealRandom = service.SearchMealbyRandom()

            if (dataMealRandom.code() == HttpURLConnection.HTTP_OK) {
                val body = dataMealRandom.body()!!.meals[0]
                val dataMeal = Meal(
                    meal = body.strMeal,
                    category = body.strCategory,
                    area = body.strArea,
                    instructions = body.strInstructions,
                    mealThumb = body.strMealThumb,
                    id = body.idMeal.toInt(),
                    linkYoutube = body.strYoutube
                )
                returnListMeal.add(dataMeal)
            }

        }
        return returnListMeal
    }

}