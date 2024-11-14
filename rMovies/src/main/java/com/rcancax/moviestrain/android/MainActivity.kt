package com.rcancax.moviestrain.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rcancax.moviestrain.Greeting
import com.rcancax.moviestrain.android.ui.MoviesApp
import com.rcancax.moviestrain.android.ui.screens.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                 MoviesApp()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MoviesTheme {
        MoviesApp()
    }
}
