@file:OptIn(ExperimentalMaterial3Api::class)

package com.rcancax.moviestrain.android.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rcancax.moviestrain.android.R
import com.rcancax.moviestrain.android.model.MoviesViewModel
import com.rcancax.moviestrain.android.ui.screens.HomeScreen

enum class MoviesScreens(@StringRes val title: Int) {
    Movie(title = R.string.top_movies),
    Home(title = R.string.top_movies)
}

@Composable
fun MoviesApp(
    viewModel: MoviesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen =MoviesScreens.valueOf(
        backStackEntry?.destination?.route ?: MoviesScreens.Home.name
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {

//            val moviesUiState: MoviesUiState = viewModel(factory = MoviesUiState.Factory)
//            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                viewModel = viewModel,
                contentPadding = it,
            )
        }
    }
}

@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}