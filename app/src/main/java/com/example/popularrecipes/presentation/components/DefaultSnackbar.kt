package com.example.popularrecipes.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSnackBar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    SnackbarHost(modifier = modifier,
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(action = {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = data.actionLabel ?: "Dismiss",
                        style = MaterialTheme.typography.body2,
                        color = Color.White,
                    )
                }
            }, modifier = Modifier.padding(16.dp)) {
                Text(
                    text = data.message,
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
        })
}