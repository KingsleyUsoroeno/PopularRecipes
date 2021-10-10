package com.example.popularrecipes.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodCategoryChip(
    foodCategory: String,
    onExecuteSearch: () -> Unit,
    isSelected: Boolean,
    onSelectCategoryChanged: (String) -> Unit,
) {

    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if(isSelected) Color.LightGray else MaterialTheme.colors.primary
    ) {

        Row(modifier = Modifier.toggleable(
            value = isSelected,
            onValueChange = {
                onSelectCategoryChanged(foodCategory)
                onExecuteSearch()
            }
        )
        ) {
            Text(
                text = foodCategory,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}