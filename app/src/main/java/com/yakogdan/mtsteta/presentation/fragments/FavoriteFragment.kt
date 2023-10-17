package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.FragmentFavoriteBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieCardsAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.GridItemDecoration
import com.yakogdan.mtsteta.presentation.screenstates.FavoriteScreenState
import com.yakogdan.mtsteta.presentation.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy { view?.findNavController() }

    private val viewModel by viewModels<FavoriteViewModel>()

    private lateinit var movieCardsAdapter: MovieCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieCards()
        initAdapter()
        lifecycleScope.launch {
            viewModel.favoriteStateFlow.collect { state ->
                processFavoriteState(state)
            }
        }

    }

    private fun processFavoriteState(state: FavoriteScreenState) {
        when (state) {
            FavoriteScreenState.Empty -> {}
            FavoriteScreenState.Error -> {}
            FavoriteScreenState.Loading -> {}
            is FavoriteScreenState.Result -> {
                movieCardsAdapter.setData(state.list)
            }
        }
    }

    private fun initAdapter() {
        movieCardsAdapter = MovieCardsAdapter(
            onItemClickListener = { movieCard ->
                navController?.navigate(
                    R.id.action_favoriteFragment_to_movieDetailsFragment,
                    bundleOf("movieCard" to movieCard)
                )
            },
            onItemLongClickListener = { movieCard ->
                viewModel.deleteMovieCard(movieCard)
            }
        )
        binding.rvMovieCard.apply {
            adapter = movieCardsAdapter
            addItemDecoration(
                GridItemDecoration(
                    start = 28, end = 28, bottom = 100
                )
            )
        }
    }
}