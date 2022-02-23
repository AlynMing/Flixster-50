package com.example.flixster

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.flixster.placeholder.PlaceholderContent.PlaceholderItem
import com.example.flixster.databinding.FragmentItemBinding
import com.example.flixster.models.Movie

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieRecyclerViewAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]

    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {


        override fun toString(): String {
            //TODO
            return ""
        }
    }

}