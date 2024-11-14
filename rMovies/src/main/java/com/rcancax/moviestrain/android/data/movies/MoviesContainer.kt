package com.rcancax.moviestrain.android.data.movies

import com.rcancax.moviestrain.android.network.MoviesApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface MoviesContainer{
    val moviesRepository: NetworkMoviesRepository
}

class DefaultAppContainer {
    private val BASE_URL = "https://api.themoviedb.org/3"
    private val API_KEY = "297b1121305106efb94b90494dd70598"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val client = OkHttpClient()

    val request = Request.Builder()
        .get()
        .url(BASE_URL)
        .addHeader(name = "api_key", value = "Bearer $API_KEY")
        .build()

    val response = client.newCall(request).execute()


}