package com.elflin.recipesapp.data.container

import com.elflin.recipesapp.data.repository.MealDBRepository
import com.elflin.recipesapp.data.service.MealDBService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealDBContainer {
    companion object{
        val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MealDBService by lazy {
        retrofit.create(MealDBService::class.java)
    }

    val retrofitRepository : MealDBRepository by lazy {
        MealDBRepository(retrofitService)
    }
}