package com.rcancax.moviestrain.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.rcancax.moviestrain.android.R
import com.rcancax.moviestrain.android.model.MoviesViewModel
import com.rcancax.moviestrain.android.network.fetchPopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen(
    viewModel: MoviesViewModel,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {

    var responseT by remember { mutableStateOf("Cargando...") }
    var moviePoster by remember { mutableStateOf("") }
    var moviesPosters by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = withContext(Dispatchers.IO) {
                fetchPopularMovies()
            }

            if (response != null) {
                responseT = "Movies: ${response.movies.size} > Movie 1: ${response.movies[0].title}. ${response.movies[0].posterPath}"
                moviePoster = response.movies[0].posterPath
                moviesPosters = response.movies.map { movie ->
                    movie.posterPath
                }
            } else {
                responseT = "Failed to fetch movies."
            }
        } catch (e: IOException) {
            responseT = "ERROR TRY-CATCH: ${e.toString()}"
        }

    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Swiper(moviesPosters = moviesPosters, modifier = Modifier.fillMaxSize().weight(1f))
    }

    

}

@Composable
fun Swiper(moviesPosters: List<String>, modifier: Modifier = Modifier) {

    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val pagerState = rememberPagerState(initialPage = 0){moviesPosters.size}

    Card(
        modifier = Modifier
            .padding(20.dp)
            .height(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("$baseUrl/${moviesPosters[page]}")
                    .build(),
                contentDescription = "Movie poster number: $page",
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),

            )
        }
    }
}