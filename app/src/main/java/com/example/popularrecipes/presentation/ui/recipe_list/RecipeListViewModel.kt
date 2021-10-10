package com.example.popularrecipes.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularrecipes.domain.model.Recipe
import com.example.popularrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    @Named(value = "auth_token") private val authToken: String,
    private val repository: RecipeRepository,
) : ViewModel() {

    val recipeState: MutableState<NetworkState> = mutableStateOf(NetworkState.Idle)
    val selectedFoodCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    val query = mutableStateOf("")

    init {
        searchForRecipe()
    }

    fun searchForRecipe() {
        recipeState.value = NetworkState.Loading
        viewModelScope.launch {
            try {
                val result = repository.searchForRecipe(
                    token = authToken,
                    page = 1,
                    query = query.value
                )
                recipeState.value = NetworkState.Loaded(recipes = result)

            } catch (e: Exception) {
                recipeState.value = NetworkState.Error(
                    e.message ?: "Something went wrong"
                )
            }
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedFoodCategory.value = newCategory
        newCategory?.let { onQueryChanged(it.value) }
    }
}


sealed class NetworkState {
    object Idle : NetworkState()
    object Loading : NetworkState()
    data class Loaded(val recipes: List<Recipe>) : NetworkState()
    data class Error(val errorMessage: String) : NetworkState()
}