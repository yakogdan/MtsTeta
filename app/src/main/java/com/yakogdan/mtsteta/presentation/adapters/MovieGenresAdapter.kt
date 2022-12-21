package com.yakogdan.mtsteta.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yakogdan.domain.entities.MovieGenresDomainEntity
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemMovieGenresBinding

class MovieGenresAdapter(
    private var onItemClickListener: (MovieGenresDomainEntity) -> Unit
) : RecyclerView.Adapter<MovieGenresAdapter.MovieGenresViewHolder>() {

    inner class MovieGenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieGenresBinding.bind(view)

        fun bind(movieGenres: MovieGenresDomainEntity) {
            binding.apply {
                tvMovieGenres.text = movieGenres.title
                root.setOnClickListener {
                    onItemClickListener(movieGenres)
                }
            }
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieGenresDomainEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieGenresDomainEntity,
            newItem: MovieGenresDomainEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MovieGenresDomainEntity,
            newItem: MovieGenresDomainEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieGenres: List<MovieGenresDomainEntity>) {
        differ.submitList(movieGenres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenresViewHolder {
        return MovieGenresViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_genres, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieGenresViewHolder, position: Int) {
        val movieGenres = differ.currentList[position]
        holder.bind(movieGenres)
    }

    override fun getItemCount(): Int = differ.currentList.size
}