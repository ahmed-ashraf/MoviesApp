package com.movies.hilt_mvvm_compose_movie.ui.screens.mainscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.hilt_mvvm_compose_movie.data.model.BaseModel
import com.movies.hilt_mvvm_compose_movie.data.repository.MovieRepository
import com.movies.hilt_mvvm_compose_movie.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    val searchData: MutableState<DataState<BaseModel>?> = mutableStateOf(null)

    fun searchApi(searchKey: String) {
        viewModelScope.launch {
            flowOf(searchKey).debounce(300)
                .filter {
                    it.trim().isEmpty().not()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    repo.search(it)
                }.collect {
                    if (it is DataState.Success) {
                        it.data
                        Timber.e(" data ${it.data.totalPages}")
                    }
                    searchData.value = it
                }
        }
    }
}