package com.example.flixster

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ShowAdapter(private val shows: List<Show>, private val mListener: OnShowListFragmentInteractionListener?)
    : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_show, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var item: Show? = null
        val showTitle: TextView = view.findViewById(R.id.fragment_movie_title)
        val showImage: ImageView = view.findViewById(R.id.fragment_movie_image)

        override fun toString(): String {
            return showTitle.text.toString() + " '"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = shows[position]

        holder.item = show
        holder.showTitle.text = show.title
        Glide.with(holder.view).load("https://image.tmdb.org/t/p/w500/${show.showImageUrl}")
            .centerInside().into(holder.showImage)

        holder.view.setOnClickListener {
            holder.item?.let { show ->
                mListener?.onItemClick(show)
            }
        }
    }

    override fun getItemCount(): Int {
        return shows.size
    }
}