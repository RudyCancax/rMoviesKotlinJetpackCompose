package com.rcancax.moviestrain.android.network

import com.rcancax.moviestrain.android.data.PopularMovies
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.http.GET

private val BASE_URL = "https://api.themoviedb.org/3"
private val API_KEY = "297b1121305106efb94b90494dd70598"

fun fetchPopularMovies(): PopularMovies? {
    val urlWithApi = "$BASE_URL/movie/popular?api_key=$API_KEY"

    val client = OkHttpClient()

    val request = Request.Builder()
        .url(urlWithApi)
        .get()
        .addHeader("accept", "application/json")
        .build()

    val response: Response = client.newCall(request).execute()

    return if (response.isSuccessful) {
        val bodyResponse = response.body?.string()
        if(bodyResponse != null) {
            Json.decodeFromString<PopularMovies>(bodyResponse)
        } else {
            null
        }
    } else {
        println("Error: ${response.code}")
        null
    }

}

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies()
}
