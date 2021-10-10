package com.example.popularrecipes.domain.mapper

import com.example.popularrecipes.domain.model.Recipe
import com.example.popularrecipes.domain.mapper.base.DomainMapper
import com.example.popularrecipes.network.model.RecipeDto
import javax.inject.Inject

class RecipeDtoMapper @Inject constructor() : DomainMapper<RecipeDto, Recipe> {
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            pk = model.pk,
            title = model.title,
            publisher = model.publisher,
            featureImage = model.featuredImage,
            rating = model.rating,
            sourceUrl = model.sourceUrl ?: "",
            description = model.description ?: "",
            cookingInstructions = model.cookingInstructions ?: "",
            ingredients = model.ingredients,
            dateAdded = model.dateAdded ?: "",
            dateUpdated = model.dateUpdated ?: ""
        )
    }

    override fun mapFromDomainModel(domain: Recipe): RecipeDto {
        return RecipeDto(
            pk = domain.pk,
            title = domain.title,
            publisher = domain.publisher,
            featuredImage = domain.featureImage,
            rating = domain.rating,
            sourceUrl = domain.sourceUrl,
            description = domain.description,
            cookingInstructions = domain.cookingInstructions,
            ingredients = domain.ingredients,
            dateAdded = domain.dateAdded,
            dateUpdated = domain.dateUpdated
        )
    }
}