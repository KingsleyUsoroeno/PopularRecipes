package com.example.popularrecipes.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.popularrecipes.presentation.ui.Screen.RecipeDetailScreen
import com.example.popularrecipes.presentation.ui.Screen.RecipeListScreen
import com.example.popularrecipes.presentation.ui.recipe.RecipeDetailScreen
import com.example.popularrecipes.presentation.ui.recipe_list.RecipeListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RecipeListScreen.routeName
    ) {
        composable(RecipeListScreen.routeName) {
            RecipeListScreen(navController = navController)
        }
        composable(
            RecipeDetailScreen.routeName + "/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                }
            )) {

            val recipeId = it.arguments?.getInt("recipeId")
            RecipeDetailScreen(recipeId = recipeId)
        }
    }
}