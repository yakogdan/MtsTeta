package com.yakogdan.mtsteta.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yakogdan.domain.entities.MovieGenreDomainEntity
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemMovieGenresBinding

class MovieGenresAdapter(
    private var onItemClickListener: (MovieGenreDomainEntity) -> Unit
) : RecyclerView.Adapter<MovieGenresAdapter.MovieGenresViewHolder>() {

    inner class MovieGenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieGenresBinding.bind(view)

        fun bind(movieGenres: MovieGenreDomainEntity) {
            binding.apply {
                tvMovieGenres.text = movieGenres.title
                root.setOnClickListener {
                    onItemClickListener(movieGenres)
                }
            }
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieGenreDomainEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieGenreDomainEntity,
            newItem: MovieGenreDomainEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MovieGenreDomainEntity,
            newItem: MovieGenreDomainEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieGenres: List<MovieGenreDomainEntity>) {
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