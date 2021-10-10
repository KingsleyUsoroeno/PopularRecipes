package com.example.popularrecipes.repository

import com.example.popularrecipes.domain.model.Recipe

interface RecipeRepository {

    suspend fun searchForRecipe(token: String, page: Int, query: String): List<Recipe>

    suspend fun getRecipeById(token: String, id: Int): Recipe
}