package com.example.popularrecipes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.popularrecipes.presentation.ui.recipe_list.FoodCategory
import com.example.popularrecipes.presentation.ui.recipe_list.getAllFoodCategories

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomToolbar(
    modifier: Modifier = Modifier,
    query: String,
    onValueChange: (category: String) -> Unit,
    onSearchClicked: () -> Unit,
    selectedFoodCategory: FoodCategory?,
    onToggleTheme: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        elevation = 8.dp,
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colors.onSurface
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp, end = 4.dp
                    )
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = query,
                    onValueChange = { onValueChange(it) },
                    label = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon",
                        )
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType =
                        KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onSearchClicked()
                            keyboardController?.hide()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Menu options icon"
                    )
                }
            }
            LazyRow(content = {
                items(getAllFoodCategories().size) { index ->
                    val foodCategory = getAllFoodCategories()[index].value
                    FoodCategoryChip(
                        foodCategory = foodCategory,
                        onExecuteSearch = onSearchClicked,
                        isSelected = selectedFoodCategory?.value == foodCategory,
                        onSelectCategoryChanged = { category ->
                            onValueChange(category)
                            onSearchClicked()
                        }
                    )
                }
            }, modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 10.dp))
        }
    }
}