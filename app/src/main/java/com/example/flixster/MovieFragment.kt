package com.example.flixster

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import com.example.flixster.models.Movie
import com.example.flixster.networking.MoviesDBApiClient

/**
 * A fragment representing a list of Items.
 */
class MovieFragment : Fragment() {
    private val movies = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context

        updateAdapter(progressBar, recyclerView)

        return view
    }

    private fun updateAdapter(pb: ContentLoadingProgressBar, rv: RecyclerView) {
        pb.show()
        val movieDBApiClient = MoviesDBApiClient()
        movies.addAll(movieDBApiClient.getResults())
    }

    companion object {

    }
}