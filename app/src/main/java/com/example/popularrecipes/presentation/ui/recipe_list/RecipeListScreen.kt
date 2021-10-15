package com.example.popularrecipes.presentation.ui.recipe_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.popularrecipes.presentation.components.CustomToolbar
import com.example.popularrecipes.presentation.components.DefaultSnackBar
import com.example.popularrecipes.presentation.components.LoadingShimmer
import com.example.popularrecipes.presentation.components.RecipeCard
import com.example.popularrecipes.presentation.ui.Screen


@Composable
fun RecipeListScreen(
    recipeListViewModel: RecipeListViewModel = hiltViewModel(),
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()

    val recipeState by recipeListViewModel.recipeState
    val query: String by recipeListViewModel.query
    val selectedFoodCategory by recipeListViewModel.selectedFoodCategory

    val recipes = if (recipeState is NetworkState.Loaded) {
        (recipeState as NetworkState.Loaded).recipes
    } else {
        emptyList()
    }

    val errorMessage = if (recipeState is NetworkState.Error) {
        (recipeState as NetworkState.Error).errorMessage
    } else {
        null
    }

    Scaffold(
        topBar = {
            CustomToolbar(
                query = query,
                onValueChange = { newWord ->
                    recipeListViewModel.onQueryChanged(newWord)
                    recipeListViewModel.onSelectedCategoryChanged(newWord)
                },
                onSearchClicked = recipeListViewModel::searchForRecipe,
                selectedFoodCategory = selectedFoodCategory,
                onToggleTheme = { }
            )
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        }
    ) {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
            if (recipeState is NetworkState.Loading) {
                LoadingShimmer()
            } else {
                LazyColumn {
                    items(recipes.size, itemContent = { index ->
                        RecipeCard(recipe = recipes[index], onClick = { recipe ->
                            navController.navigate(Screen.RecipeDetailScreen.withArgs(recipe.pk))
                        })
                    })
                }
            }
            if (errorMessage != null) {
                DefaultSnackBar(
                    snackbarHostState = scaffoldState.snackbarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onDismiss = {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    }
                )
            }
        }
    }
}