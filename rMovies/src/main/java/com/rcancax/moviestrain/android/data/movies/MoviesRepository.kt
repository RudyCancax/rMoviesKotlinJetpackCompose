package com.rcancax.moviestrain.android.data.movies

import com.rcancax.moviestrain.android.network.MoviesApiService

class NetworkMoviesRepository(
    private val moviesApiService: MoviesApiService
) {
    suspend fun getPopularMovies() = moviesApiService.getPopularMovies()
}