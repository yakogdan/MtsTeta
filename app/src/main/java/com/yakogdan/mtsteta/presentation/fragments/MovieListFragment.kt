package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.FragmentMovieListBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieCardsAdapter
import com.yakogdan.mtsteta.presentation.adapters.MovieGenresAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.GridItemDecoration
import com.yakogdan.mtsteta.presentation.itemdecoration.HorizontalItemDecoration
import com.yakogdan.mtsteta.presentation.screenstates.MovieListScreenState
import com.yakogdan.mtsteta.presentation.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy { view?.findNavController() }

    private val viewModel by viewModels<MovieListViewModel>()

    private lateinit var movieGenresAdapter: MovieGenresAdapter
    private lateinit var movieCardsAdapter: MovieCardsAdapter

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
        viewModel.getMovieGenres()
        viewModel.getMovieCards()
        initAdapters()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMovieCards()
        }
        lifecycleScope.launch {
            viewModel.movieListStateFlow.collect { state ->
                when (state) {
                    is MovieListScreenState.Error -> {
                        Toast.makeText(context, "Ошибка загрузки. Включите VPN.", Toast.LENGTH_LONG)
                            .show()
                        binding.swipeRefreshLayout.isRefreshing = false
                    }

                    is MovieListScreenState.Loading -> {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }

                    is MovieListScreenState.Result -> {
                        movieCardsAdapter.setData(state.list)
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.movieGenresStateFlow.collect { movieGenres ->
                movieGenresAdapter.setData(movieGenres)
            }
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
        movieCardsAdapter = MovieCardsAdapter(
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
                    HorizontalItemDecoration(
                        startFirst = 54,
                        endAll = 25
                    )
                )
            }
            rvMovieCard.apply {
                adapter = movieCardsAdapter
                addItemDecoration(
                    GridItemDecoration(
                        start = 28,
                        end = 28,
                        bottom = 100
                    )
                )
            }
        }
    }
}