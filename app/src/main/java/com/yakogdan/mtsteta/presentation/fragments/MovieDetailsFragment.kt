package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.yakogdan.mtsteta.databinding.FragmentMovieDetailsBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieGenresAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.MovieGenresItemDecoration
import com.yakogdan.mtsteta.presentation.viewmodels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val bundleArgs: MovieDetailsFragmentArgs by navArgs()

    private val viewModel by viewModels<MovieDetailsViewModel>()

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
        initAdapter()
        bundleArgs.movieCard?.let { movieCard ->
            viewModel.getMovieDetailFromApi(movieCard.id)
            viewModel.movieDetailLiveData.observe(viewLifecycleOwner) { movieDetails ->
                movieGenresAdapter.setData(movieDetails.genres)
                with(binding) {
                    tvMovieTitle.text = movieDetails.title
                    tvDate.text = movieDetails.release_date
                    tvMovieDescription.text = movieDetails.overview
                    rb.rating = movieDetails.vote_average.toFloat()
                    tvAgeRestriction.text = getAgeRestriction(movieDetails.adult)
                    ivMoviePoster.load("https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces" + movieCard.posterUrl)
                }
            }
        }
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

    private fun getAgeRestriction(adult: Boolean): String =
        if (adult) {
            "18+"
        } else {
            "12+"
        }
}