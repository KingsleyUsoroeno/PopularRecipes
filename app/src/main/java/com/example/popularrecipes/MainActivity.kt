package com.example.popularrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.popularrecipes.presentation.ui.Navigation
import com.example.popularrecipes.presentation.ui.recipe.RecipeDetailViewModel
import com.example.popularrecipes.presentation.ui.recipe_list.RecipeListViewModel
import com.example.popularrecipes.presentation.ui.theme.PopularRecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RecipeListViewModel by viewModels()
    private val recipeDetailViewModel: RecipeDetailViewModel by viewModels()

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
                    Navigation()
                }
            }
        }
    }
}


@Composable
@Preview
fun RecipeListPreview() {
    Navigation()
}