package com.example.flixster.models

import org.json.JSONArray

data class Movie(
    val movieId: Int,
    private val posterPath: String,
    private val backdropPath: String,
    val title: String,
    val overview: String,
    val stars: Double,
) {

    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"
    val backdropURL = "https://image.tmdb.org/t/p/w780/$backdropPath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i  in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("backdrop_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getDouble("vote_average")
                    )
                )
            }
            return movies
        }
    }
}