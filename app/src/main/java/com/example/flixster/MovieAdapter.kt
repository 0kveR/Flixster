package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>, private val mListener: OnMovieListFragmentInteractionListener?)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var item: Movie? = null
        val movieTitle: TextView = view.findViewById(R.id.fragment_movie_title)
        val movieImage: ImageView = view.findViewById(R.id.fragment_movie_image)

        override fun toString(): String {
            return movieTitle.text.toString() + " '"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.item = movie
        holder.movieTitle.text = movie.title
        Glide.with(holder.view).load("https://image.tmdb.org/t/p/w500/${movie.movieImageUrl}")
            .centerInside().into(holder.movieImage)

        holder.view.setOnClickListener {
            holder.item?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}