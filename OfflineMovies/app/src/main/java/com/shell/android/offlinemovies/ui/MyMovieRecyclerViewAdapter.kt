package com.shell.android.offlinemovies.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shell.android.offlinemovies.BuildConfig
import com.shell.android.offlinemovies.R
import com.shell.android.offlinemovies.data.local.entity.MovieEntity
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MyMovieRecyclerViewAdapter(
    private val context: Context,
    private val mValues: List<MovieEntity>
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        private val imgCover: ImageView = mView.imgCover

        lateinit var movie: MovieEntity

        fun bind(movie: MovieEntity) {
            this.movie = movie
            Glide.with(context)
                .load("${BuildConfig.IMAGE_URL}${movie.posterPath}")
                .centerCrop()
                .into(imgCover)
        }
    }
}
