package com.yakogdan.mtsteta.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemMovieCardBinding

class MovieCardAdapter(
    private var onItemClickListener: (MovieCardDomainEntity) -> Unit
) : RecyclerView.Adapter<MovieCardAdapter.MovieCardViewHolder>() {

    inner class MovieCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMovieCardBinding.bind(view)

        fun bind(movieCard: MovieCardDomainEntity) {
            binding.apply {
                ivItemMoviePoster.load(movieCard.imageUrl)
                tvItemMovieTitle.text = movieCard.title
                tvItemMovieDescription.text = movieCard.description
                val ageRestriction = movieCard.ageRestriction.toString() + "+"
                tvAgeRestriction.text = ageRestriction
                rbMovie.rating = movieCard.rateScore.toFloat()
                root.setOnClickListener {
                    onItemClickListener(movieCard)
                }
            }
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieCardDomainEntity>() {
        override fun areItemsTheSame(
            oldItem: MovieCardDomainEntity,
            newItem: MovieCardDomainEntity
        ): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MovieCardDomainEntity,
            newItem: MovieCardDomainEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieCards: List<MovieCardDomainEntity>) {
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