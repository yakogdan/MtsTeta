package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.FragmentMovieListBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieCardAdapter
import com.yakogdan.mtsteta.presentation.adapters.MovieGenresAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.CardItemDecoration
import com.yakogdan.mtsteta.presentation.itemdecoration.MovieGenresItemDecoration
import com.yakogdan.mtsteta.presentation.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy { view?.findNavController() }

    private val viewModel by viewModels<MovieListViewModel>()

    private lateinit var movieGenresAdapter: MovieGenresAdapter
    private lateinit var movieCardAdapter: MovieCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (viewModel.movieCardDbIsEmpty()) {
//            Log.d("myTAG", "dbIsEmpty = true")
//            Toast.makeText(context, "repo", Toast.LENGTH_SHORT).show()
//            viewModel.getMovieCardsFromRepo()
//            viewModel.movieCardLiveData.observe(viewLifecycleOwner) { movieCards ->
//                viewModel.addMovieCards(movieCards)
//            }
//        } else {
//            Log.d("myTAG", "dbIsEmpty = false")
//            Toast.makeText(context, "db", Toast.LENGTH_SHORT).show()
//            viewModel.getMovieCardsFromDB()
//        }
        viewModel.getMovieCards()
        viewModel.getMovieGenres()
        initAdapters()

        viewModel.movieCardLiveData.observe(viewLifecycleOwner) { movieCards ->
            movieCardAdapter.setData(movieCards)
        }
        viewModel.movieGenresLiveData.observe(viewLifecycleOwner) { movieGenres ->
            movieGenresAdapter.setData(movieGenres)
        }
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
        movieCardAdapter = MovieCardAdapter(
            onItemClickListener = { movieCard ->
                navController?.navigate(
                    R.id.action_movieListFragment_to_movieDetailsFragment,
                    bundleOf("movieCard" to movieCard)
                )
            }
        )
        binding.apply {
            rvMovieGenres.apply {
                adapter = movieGenresAdapter
                addItemDecoration(
                    MovieGenresItemDecoration(
                        startFirst = 54,
                        endAll = 25
                    )
                )
            }
            rvMovieCard.apply {
                adapter = movieCardAdapter
                addItemDecoration(
                    CardItemDecoration(
                        start = 28,
                        end = 28,
                        bottom = 100
                    )
                )
            }
        }
    }
}