package com.yakogdan.mtsteta.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.ItemActorCardBinding

class MovieActorsAdapter : RecyclerView.Adapter<MovieActorsAdapter.MovieActorsViewHolder>() {

    inner class MovieActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemActorCardBinding.bind(view)

        fun bind(movieActorDomain: MovieActorDomain) {
            binding.apply {
                if (movieActorDomain.profilePath != null) {
                    ivActorPhoto
                        .load(
                            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" +
                                    movieActorDomain.profilePath
                        )
                } else {
                    ivActorPhoto.setImageResource(R.drawable.ic_image_not_found)
                }
                tvActorName.text = movieActorDomain.name
            }
        }
    }

    private val callback = object : DiffUtil.ItemCallback<MovieActorDomain>() {
        override fun areItemsTheSame(
            oldItem: MovieActorDomain,
            newItem: MovieActorDomain
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: MovieActorDomain,
            newItem: MovieActorDomain
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun setData(movieActors: List<MovieActorDomain>) {
        differ.submitList(movieActors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieActorsViewHolder =
        MovieActorsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_actor_card,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: MovieActorsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}