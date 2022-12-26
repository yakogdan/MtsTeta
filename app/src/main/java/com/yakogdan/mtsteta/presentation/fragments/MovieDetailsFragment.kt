package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.yakogdan.domain.entities.MovieGenresDomainEntity
import com.yakogdan.mtsteta.databinding.FragmentMovieDetailsBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieGenresAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.MovieGenresItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val bundleArgs: MovieDetailsFragmentArgs by navArgs()

    private lateinit var movieGenresAdapter: MovieGenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundleArgs.movieCard?.let { movieCard ->
            with(binding) {
                tvMovieTitle.text = movieCard.title
                val date = "22.11.2022"
                tvDate.text = date
                tvMovieDescription.text = movieCard.description
                rb.rating = movieCard.rateScore.toFloat()
                val ageRestriction = movieCard.ageRestriction.toString() + "+"
                tvAgeRestriction.text = ageRestriction
                ivMoviePoster.load(movieCard.imageUrl)
            }
        }
        initAdapter()
        movieGenresAdapter.setData(
            listOf(
                MovieGenresDomainEntity(title = "боевик"),
                MovieGenresDomainEntity(title = "комедия")
            )
        )
    }


    private fun initAdapter() {
        movieGenresAdapter = MovieGenresAdapter(
            onItemClickListener = { movieGenres ->
                Toast.makeText(
                    context,
                    movieGenres.title,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        binding.apply {
            rvGenresDM.apply {
                adapter = movieGenresAdapter
                addItemDecoration(
                    MovieGenresItemDecoration(
                        startFirst = 54,
                        endAll = 25
                    )
                )
            }
        }
    }
}