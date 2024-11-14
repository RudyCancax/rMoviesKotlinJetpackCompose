package com.rcancax.moviestrain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform