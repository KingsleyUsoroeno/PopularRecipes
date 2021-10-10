package com.example.popularrecipes.di

import com.example.popularrecipes.domain.mapper.RecipeDtoMapper
import com.example.popularrecipes.network.RecipeService
import com.example.popularrecipes.repository.RecipeRepository
import com.example.popularrecipes.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            recipeService = recipeService,
            recipeDtoMapper = recipeMapper
        )
    }
}