package com.example.popularrecipes.presentation.ui.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularrecipes.domain.model.Recipe
import com.example.popularrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @Named(value = "auth_token") private val authToken: String,
) : ViewModel() {

    val fetchRecipeState = mutableStateOf<FetchRecipeState>(FetchRecipeState.Idle)

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            fetchRecipeState.value = FetchRecipeState.Loading
            try {
                val recipe = recipeRepository.getRecipeById(token = authToken, id = recipeId)
                println("recipe is $recipe")
                fetchRecipeState.value = FetchRecipeState.Loaded(recipe = recipe)
            } catch (e: Exception) {
                fetchRecipeState.value = FetchRecipeState.Error(
                    e.message
                        ?: "Something went wrong"
                )
            }
        }
    }
}

sealed class FetchRecipeState {
    object Idle : FetchRecipeState()
    object Loading : FetchRecipeState()
    data class Loaded(val recipe: Recipe) : FetchRecipeState()
    data class Error(val errorMessage: String) : FetchRecipeState()
}