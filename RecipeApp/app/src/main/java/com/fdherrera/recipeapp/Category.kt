package com.fdherrera.recipeapp

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val imageUrl: String,
    @SerializedName("strCategoryDescription") val description: String,
)

data class CategoriesResponse(
    val categories: List<Category>
)