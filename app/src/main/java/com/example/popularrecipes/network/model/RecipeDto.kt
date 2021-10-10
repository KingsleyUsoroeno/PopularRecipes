package com.example.popularrecipes.network.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("pk")
    var pk: Int,
    var title: String,
    var publisher: String,
    @SerializedName("featured_image")
    var featuredImage: String,
    var rating: Int = 0,
    @SerializedName("source_url")
    var sourceUrl: String?,
    var description: String?,
    var cookingInstructions: String?,
    var ingredients: List<String>,
    @SerializedName("date_added")
    var dateAdded: String?,
    @SerializedName("date_updated")
    var dateUpdated: String?,
)