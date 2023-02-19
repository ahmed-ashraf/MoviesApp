package com.movies.hilt_mvvm_compose_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.movies.hilt_mvvm_compose_movie.data.datasource.remote.ApiService
import com.movies.hilt_mvvm_compose_movie.data.datasource.remote.paging.*
import com.movies.hilt_mvvm_compose_movie.data.model.BaseModel
import com.movies.hilt_mvvm_compose_movie.data.model.moviedetail.MovieDetail
import com.movies.hilt_mvvm_compose_movie.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun search(searchKey: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.search(searchKey)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


    fun nowPlayingPagingDataSource() = Pager(
        pagingSourceFactory = { NowPlayingPagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

}