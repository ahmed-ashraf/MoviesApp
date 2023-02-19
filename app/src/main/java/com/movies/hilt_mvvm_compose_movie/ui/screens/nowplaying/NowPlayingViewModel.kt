package com.movies.hilt_mvvm_compose_movie.ui.screens.nowplaying


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.movies.hilt_mvvm_compose_movie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(repo: MovieRepository) : ViewModel() {
    val popularMovies = repo.nowPlayingPagingDataSource().cachedIn(viewModelScope)
}