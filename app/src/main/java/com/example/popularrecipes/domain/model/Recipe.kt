package com.example.popularrecipes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val pk: Int,
    val title: String,
    val publisher: String,
    val featureImage: String,
    val rating: Int,
    val sourceUrl: String,
    val description: String,
    val cookingInstructions: String,
    val ingredients: List<String>,
    val dateAdded: String,
    val dateUpdated: String
) : Parcelable