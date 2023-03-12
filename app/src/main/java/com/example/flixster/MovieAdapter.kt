package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>, private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var item: Movie? = null
        val movieTitle: TextView = view.findViewById<View>(R.id.movie_title) as TextView
        val movieImage: ImageView = view.findViewById(R.id.movie_image)
        val movieDesc: TextView = view.findViewById<View>(R.id.movie_description) as TextView

        override fun toString(): String {
            return movieTitle.text.toString() + " '" + movieDesc.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.item = movie
        holder.movieTitle.text = movie.title
        Glide.with(holder.view).load("https://image.tmdb.org/t/p/w500/${movie.movieImageUrl}")
            .centerInside().into(holder.movieImage)
        holder.movieDesc.text = movie.description

        holder.view.setOnClickListener {
            holder.item?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}