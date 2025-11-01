package com.elflin.recipesapp.data.service

import com.elflin.recipesapp.data.dto.ResponseAPIMeal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBService {

    @GET("search.php")
    suspend fun SearchMealbyName(
        @Query("s") name: String
    ): Response<ResponseAPIMeal>

    @GET("lookup.php")
    suspend fun SearchMealbyId(
        @Query("i") id: Int
    ): Response<ResponseAPIMeal>

    @GET("search.php")
    suspend fun SearchMealbyFirstLetter(
        @Query("f") keyLetter: String
    ): Response<ResponseAPIMeal>

    @GET("random.php")
    suspend fun SearchMealbyRandom(
    ): Response<ResponseAPIMeal>

}