package com.movies.hilt_mvvm_compose_movie.ui.screens.moviedetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.hilt_mvvm_compose_movie.data.model.BaseModel
import com.movies.hilt_mvvm_compose_movie.data.model.moviedetail.MovieDetail
import com.movies.hilt_mvvm_compose_movie.data.repository.MovieRepository
import com.movies.hilt_mvvm_compose_movie.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    val movieDetail: MutableState<DataState<MovieDetail>?> = mutableStateOf(null)
    val recommendedMovie: MutableState<DataState<BaseModel>?> = mutableStateOf(null)

    fun movieDetailApi(movieId: Int) {
        viewModelScope.launch {
            repo.movieDetail(movieId).onEach {
                movieDetail.value = it
            }.launchIn(viewModelScope)
        }
    }
}