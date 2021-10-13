package com.example.popularrecipes.repository

import com.example.popularrecipes.domain.mapper.RecipeDtoMapper
import com.example.popularrecipes.domain.model.Recipe
import com.example.popularrecipes.network.RecipeService
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDtoMapper: RecipeDtoMapper,
    private val recipeService: RecipeService
) : RecipeRepository {

    override suspend fun searchForRecipe(token: String, page: Int, query: String): List<Recipe> {
        val recipeResponse = recipeService.search(token, page, query)
        return recipeDtoMapper.mapToDomainList(recipeResponse.recipes)
    }

    override suspend fun getRecipeById(token: String, id: Int): Recipe {
        val recipe = recipeService.get(token, id)
        return recipeDtoMapper.mapToDomainModel(recipe)
    }
}