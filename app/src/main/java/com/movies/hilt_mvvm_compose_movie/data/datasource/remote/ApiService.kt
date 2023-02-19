package com.movies.hilt_mvvm_compose_movie.data.datasource.remote

import com.movies.hilt_mvvm_compose_movie.data.model.BaseModel
import com.movies.hilt_mvvm_compose_movie.data.model.moviedetail.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiURL.MOVIE_LIST)
    suspend fun nowPlayingMovieList(@Query("page") page: Int): BaseModel

    @GET(ApiURL.MOVIE_DETAIL)
    suspend fun movieDetail(@Path("movieId") movieId: Int): MovieDetail

    @GET(ApiURL.SEARCH_MOVIE)
    suspend fun search(@Query("query") searchKey: String): BaseModel
}