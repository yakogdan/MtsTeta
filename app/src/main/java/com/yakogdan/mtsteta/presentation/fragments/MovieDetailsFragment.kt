package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.yakogdan.mtsteta.databinding.FragmentMovieDetailsBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieActorsAdapter
import com.yakogdan.mtsteta.presentation.adapters.MovieGenresAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.HorizontalItemDecoration
import com.yakogdan.mtsteta.presentation.viewmodels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val bundleArgs: MovieDetailsFragmentArgs by navArgs()

    private val viewModel by viewModels<MovieDetailsViewModel>()

    private lateinit var movieGenresAdapter: MovieGenresAdapter

    private lateinit var movieActorsAdapter: MovieActorsAdapter

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
        initAdapters()
        bundleArgs.movieCard?.let { movieCard ->
            val movieId = movieCard.id
            viewModel.apply {
                getMovieDetailFromApi(movieId)
                getMovieActorsFromApi(movieId)
                Log.d("myTAG", "апи прошёл")
                movieDetailLiveData.observe(viewLifecycleOwner) { movieDetails ->
                    movieGenresAdapter.setData(movieDetails.genres)
                    with(binding) {
                        ivMoviePoster.load("https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces" + movieCard.posterUrl)
                        tvMovieTitle.text = movieDetails.title
                        rb.rating = movieDetails.vote_average.toFloat() / 2
                        tvDate.text = movieDetails.release_date
                        tvAgeRestriction.text = getAgeRestriction(movieDetails.adult)
                        tvMovieDescription.text = movieDetails.overview
                    }
                }
            }
        }
        viewModel.movieActorsLiveData.observe(viewLifecycleOwner) { movieActors ->
            movieActorsAdapter.setData(movieActors)
        }
//        movieActorsAdapter.setData(
//            MovieActorsDomain(listOf(
//                ActorDomain(
//                    name = "LKDSj",
//                    profilePath = "/iWIUEwgn2KW50MssR7tdPeFoRGW.jpg"
//                ),
//                ActorDomain(
//                    name = "LKDfghjfgSj",
//                    profilePath = "/iWIUEwgn2KW50MssR7tdPeFoRGW.jpg"
//                )
//            ),
//            1)
//        )
    }

    private fun initAdapters() {
        movieGenresAdapter = MovieGenresAdapter(
            onItemClickListener = { movieGenres ->
                Toast.makeText(
                    context,
                    movieGenres.title,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        movieActorsAdapter = MovieActorsAdapter()
        binding.apply {
            rvGenresDM.apply {
                adapter = movieGenresAdapter
                addItemDecoration(
                    HorizontalItemDecoration(
                        startFirst = 54,
                        endAll = 25
                    )
                )
            }
            rvActors.apply {
                adapter = movieActorsAdapter
                addItemDecoration(
                    HorizontalItemDecoration(
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