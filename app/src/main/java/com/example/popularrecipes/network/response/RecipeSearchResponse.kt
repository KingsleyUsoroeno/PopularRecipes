package com.example.popularrecipes.network.response

import com.example.popularrecipes.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    val count: Int,
    @SerializedName("results")
    val recipes: List<RecipeDto>,
)