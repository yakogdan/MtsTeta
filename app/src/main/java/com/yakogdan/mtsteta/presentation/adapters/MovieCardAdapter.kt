package com.yakogdan.mtsteta.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemMovieCardBinding

class MovieCardAdapter(
    private var onItemClickListener: (MovieCardDomain) -> Unit
) : RecyclerView.Adapter<MovieCardAdapter.MovieCardViewHolder>() {

    inner class MovieCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMovieCardBinding.bind(view)

        fun bind(movieCard: MovieCardDomain) {
            binding.apply {
                ivItemMoviePoster.load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movieCard.posterUrl)
                tvItemMovieTitle.text = movieCard.title
                tvItemMovieDescription.text = movieCard.description
                val ageRestriction = movieCard.ageRestriction.toString() + "+"
                tvAgeRestriction.text = ageRestriction
                rbMovie.rating = movieCard.rateScore.toFloat()/2
                root.setOnClickListener {
                    onItemClickListener(movieCard)
                }
            }
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieCardDomain>() {
        override fun areItemsTheSame(
            oldItem: MovieCardDomain,
            newItem: MovieCardDomain
        ): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MovieCardDomain,
            newItem: MovieCardDomain
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieCards: List<MovieCardDomain>) {
        differ.submitList(movieCards)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        return MovieCardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}