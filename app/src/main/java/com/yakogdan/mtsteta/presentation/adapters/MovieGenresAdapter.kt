package com.yakogdan.mtsteta.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemMovieGenresBinding

class MovieGenresAdapter(
    private var onItemClickListener: (MovieGenreDomain) -> Unit
) : RecyclerView.Adapter<MovieGenresAdapter.MovieGenresViewHolder>() {

    inner class MovieGenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieGenresBinding.bind(view)

        fun bind(movieGenres: MovieGenreDomain) {
            binding.apply {
                tvMovieGenres.text = movieGenres.title
                root.setOnClickListener {
                    onItemClickListener(movieGenres)
                }
            }
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieGenreDomain>() {
        override fun areItemsTheSame(
            oldItem: MovieGenreDomain,
            newItem: MovieGenreDomain
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MovieGenreDomain,
            newItem: MovieGenreDomain
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieGenres: List<MovieGenreDomain>) {
        differ.submitList(movieGenres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder =
        MovieGenresViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_movie_genres,
                    parent,
                    false
                )
        )


    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        val movieGenres = differ.currentList[position]
        holder.bind(movieGenres)
    }

    override fun getItemCount(): Int = differ.currentList.size
}