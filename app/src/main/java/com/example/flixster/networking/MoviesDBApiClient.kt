package com.example.flixster.networking

import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixster.models.Movie
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MoviesDBApiClient"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MoviesDBApiClient {
    var movies = mutableListOf<Movie>()

    init {
        val client = AsyncHttpClient()
        client.get(API_KEY, object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON data $json")
                try {
                    val movieJsonArray = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(movieJsonArray))
                    Log.i(TAG, "Movie list $movies")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }
        })
    }

    fun getResults(): List<Movie> {
        return movies
    }

}