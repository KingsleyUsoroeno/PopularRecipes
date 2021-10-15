package com.example.popularrecipes.presentation.ui.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.popularrecipes.R
import com.example.popularrecipes.domain.model.Recipe
import com.example.popularrecipes.presentation.components.LoadingShimmer
import com.example.popularrecipes.util.loadImage

@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
    recipeDetailViewModel: RecipeDetailViewModel = hiltViewModel(),
) {

    recipeId?.let { nonNullRecipeId ->
        LaunchedEffect(key1 = nonNullRecipeId) {  // cancelled / relaunched when
            // nonNullRecipeId varies
            recipeDetailViewModel.loadRecipe(recipeId = nonNullRecipeId)
        }

        val recipeState = recipeDetailViewModel.fetchRecipeState.value

        Surface(modifier = Modifier.fillMaxSize()) {

            when (recipeState) {
                is FetchRecipeState.Idle -> {
                }

                is FetchRecipeState.Loading -> {
                    LoadingShimmer()
                }
                is FetchRecipeState.Loaded -> {
                    RecipeView(recipe = recipeState.recipe)
                }

                is FetchRecipeState.Error -> {
                    println("error is ${recipeState.errorMessage}")
                }
            }
        }
    }
}


@Composable
fun RecipeView(recipe: Recipe) {

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(state = scrollState)) {
        with(recipe) {
            loadImage(
                LocalContext.current, featureImage,
                R.drawable.empty_plate
            ).let { image ->
                image.value?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "recipe image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h5
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                for (ingredient in ingredients) {
                    Text(
                        text = ingredient, modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(
                    text = cookingInstructions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}