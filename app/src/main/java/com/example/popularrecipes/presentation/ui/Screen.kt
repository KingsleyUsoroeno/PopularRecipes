package com.example.popularrecipes.presentation.ui

sealed class Screen(val routeName: String) {
    object RecipeListScreen : Screen("recipe_list_screen")
    object RecipeDetailScreen : Screen("recipe_detail_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(routeName)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
