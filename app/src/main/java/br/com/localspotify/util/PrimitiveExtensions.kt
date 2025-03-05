package br.com.localspotify.util

fun Float.formatProgressByDuration(totalDuration: Long): String {
    val currentTime = (this * totalDuration) / 100
    return currentTime.toLong().formatDuration()
}

fun Long.toPercentOf(duration: Long): Float {
    return if (this > 0) (this.toFloat() / duration.toFloat()) * 100f else 0f
}

fun Long.formatDuration(): String {
    val totalSeconds = this / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}