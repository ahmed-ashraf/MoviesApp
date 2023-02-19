package com.movies.hilt_mvvm_compose_movie.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.movies.hilt_mvvm_compose_movie.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_title,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Home : Screen("home_screen")

    object MovieDetail :
        Screen("movie_detail_screen", objectName = "movieItem", objectPath = "/{movieItem}")

}