package com.example.flixster

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide

import com.example.flixster.models.Movie
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 * TODO: Replace the implementation with code for your data type.
 */

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MyMovieRecyclerViewAdapter(
    private val context: Context,
    private val movies: List<Movie>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val MOVIE = 0
    private val POP = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = when (viewType) {
            MOVIE -> {
                val view: View = inflater.inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view)
            }
            POP -> {
                val view: View = inflater.inflate(R.layout.pop_movie_item, parent, false)
                PopMovieViewHolder(view)
            }
            else -> {
                val view: View = inflater.inflate(R.layout.movie_item, parent, false)
                MovieViewHolder(view)
            }
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movies[position]
        when(holder.itemViewType) {
            MOVIE -> {
                val view = holder as MovieViewHolder
                view.bind(movie)
            }
            POP -> {
                val view = holder as PopMovieViewHolder
                Log.i("Test pop movie", movie.stars.toString())
                view.bind(movie)
            }
            else -> {
                val view = holder as MovieViewHolder
                view.bind(movie)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemViewType(position: Int): Int {
        if (movies[position].stars < 5.0) {
            return MOVIE
        }
        return POP
    }

    inner class MovieViewHolder(binding: View) : RecyclerView.ViewHolder(binding), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            val toLoad: String
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                toLoad = movie.posterImageURL
            }
            else {
                toLoad = movie.backdropURL
            }
            Glide.with(context)
                .load(toLoad)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_warning)
                .transform(RoundedCornersTransformation(30, 10))
                .into(ivPoster)
        }

        override fun onClick(v: View?) {
            val movie = movies[adapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            val poster = androidx.core.util.Pair<View, String>(ivPoster, "poster")
            val title = androidx.core.util.Pair<View, String>(tvTitle, "title")
            val overview = androidx.core.util.Pair<View, String>(tvOverview, "overview")
            val option = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context as Activity, poster, title, overview)
            context.startActivity(intent, option.toBundle())
        }
    }

    inner class PopMovieViewHolder(binding: View) : RecyclerView.ViewHolder(binding), View.OnClickListener {
        private val ivBackDrop = itemView.findViewById<ImageView>(R.id.ivBackDrop)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            Glide.with(context)
                .load(movie.backdropURL)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_warning)
                .transform(RoundedCornersTransformation(30, 10))
                .into(ivBackDrop)
        }

        override fun onClick(v: View?) {
            val movie = movies[adapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            val poster = androidx.core.util.Pair<View, String>(ivBackDrop, "poster")
            val option = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context as Activity, poster)
            context.startActivity(intent, option.toBundle())
        }
    }
}