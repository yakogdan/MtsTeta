package com.yakogdan.mtsteta.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemMovieCardBinding

class MovieCardsAdapter(
    private var onItemClickListener: (MovieCardDomain) -> Unit,
    private var onItemLongClickListener: (MovieCardDomain) -> Unit
) : RecyclerView.Adapter<MovieCardsAdapter.MovieCardViewHolder>() {

    inner class MovieCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMovieCardBinding.bind(view)

        fun bind(movieCard: MovieCardDomain) {
            binding.apply {
                ivItemMoviePoster
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movieCard.posterPath)
                tvItemMovieTitle.text = movieCard.title
                tvItemMovieDescription.text = movieCard.description
                tvAgeRestriction.text = getAgeRestriction(movieCard)
                rbMovie.rating = movieCard.voteAverage.toFloat() / 2
                root.setOnClickListener {
                    onItemClickListener(movieCard)
                }
                root.setOnLongClickListener {
                    onItemLongClickListener(movieCard)
                    return@setOnLongClickListener true
                }
            }
        }
    }

    private fun getAgeRestriction(movieCard: MovieCardDomain): String {
        return if (movieCard.adult) {
            "18+"
        } else {
            "12+"
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieCardDomain>() {
        override fun areItemsTheSame(
            oldItem: MovieCardDomain, newItem: MovieCardDomain
        ): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MovieCardDomain, newItem: MovieCardDomain
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieCards: List<MovieCardDomain>) {
        differ.submitList(movieCards)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder =
        MovieCardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie_card, parent, false
            )
        )


    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}