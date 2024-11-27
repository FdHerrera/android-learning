package com.fdherrera.recipeapp

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val imageUrl: String,
)

data class MealsResponse(
    val meals: List<Meal> = emptyList()
)