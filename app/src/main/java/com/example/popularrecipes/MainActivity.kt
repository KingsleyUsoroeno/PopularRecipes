package com.example.popularrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.popularrecipes.presentation.ui.recipe_list.FoodCategory
import com.example.popularrecipes.presentation.ui.recipe_list.NetworkState
import com.example.popularrecipes.presentation.ui.recipe_list.RecipeList
import com.example.popularrecipes.presentation.ui.recipe_list.RecipeListViewModel
import com.example.popularrecipes.presentation.ui.theme.PopularRecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDark = mutableStateOf(false)
            PopularRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val recipeState by viewModel.recipeState
                    val query: String by viewModel.query
                    val selectedFoodCategory by viewModel.selectedFoodCategory

                    val recipes = if (recipeState is NetworkState.Loaded) {
                        (recipeState as NetworkState.Loaded).recipes
                    } else {
                        emptyList()
                    }

                    RecipeList(
                        recipes = recipes,
                        query = query,
                        onValueChange = { newWord ->
                            viewModel.onQueryChanged(newWord)
                            viewModel.onSelectedCategoryChanged(newWord)
                        },
                        onSearchClicked = viewModel::searchForRecipe,
                        selectedFoodCategory = selectedFoodCategory,
                        showProgressbar = recipeState is NetworkState.Loading,
                        onToggleTheme = { isDark.value = !isDark.value }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun RecipeListPreview() {
    RecipeList(
        recipes = listOf(), query = "",
        onValueChange = {},
        onSearchClicked = {},
        selectedFoodCategory = FoodCategory.SOUP,
        showProgressbar = true,
        onToggleTheme = {}
    )
}