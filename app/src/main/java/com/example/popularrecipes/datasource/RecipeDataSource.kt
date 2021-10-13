package com.example.popularrecipes.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popularrecipes.domain.model.Recipe
import com.example.popularrecipes.repository.RecipeRepository
import retrofit2.HttpException
import java.io.IOException

class RecipeDataSource(
    private val recipeRepository: RecipeRepository,
    private val authToken: String,
) : PagingSource<Int, Recipe>() {

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val nextPage = params.key ?: 1
            val recipes = recipeRepository.searchForRecipe(
                token = authToken,
                page = nextPage,
                query = ""
            )
            LoadResult.Page(
                data = recipes,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (recipes.isEmpty()) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}