package com.movies.hilt_mvvm_compose_movie.ui.screens.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.movies.hilt_mvvm_compose_movie.ui.component.HomeScreen

@Composable
fun NowPlaying(
    navController: NavController,
) {
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(
        navController = navController,
        movies = nowPlayViewModel.popularMovies
    )
}