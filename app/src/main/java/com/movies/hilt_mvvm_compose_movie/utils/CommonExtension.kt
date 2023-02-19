package com.movies.hilt_mvvm_compose_movie.utils
import kotlin.time.Duration.Companion.minutes


fun Int.hourMinutes(): String {
    return "${this.minutes.inWholeHours}h ${this % 60}m"
}
