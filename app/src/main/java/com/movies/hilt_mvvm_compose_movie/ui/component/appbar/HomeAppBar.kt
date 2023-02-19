package com.movies.hilt_mvvm_compose_movie.ui.component.appbar

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.movies.hilt_mvvm_compose_movie.ui.theme.Purple500

@Composable
fun HomeAppBar(title: String, openDrawer: () -> Unit, openFilters: () -> Unit) {
    TopAppBar(
        backgroundColor = Purple500,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
            )
        },
    )
}
