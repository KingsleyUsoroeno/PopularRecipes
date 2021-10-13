package com.example.popularrecipes.presentation.ui.recipe

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun RecipeDetailScreen(
    recipeId: Int?, viewModel: RecipeDetailViewModel,
    loadRecipe: (recipeId: Int) -> Unit
) {

    recipeId?.let {
        // loadRecipe(it)

        val recipeState by viewModel.fetchRecipeState

        println("recipeState is $recipeState")

        Text(text = recipeId.toString())
    }
}