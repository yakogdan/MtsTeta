package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.databinding.FragmentMovieDetailsBinding
import com.yakogdan.mtsteta.presentation.screenstates.MovieActorsScreenState
import com.yakogdan.mtsteta.presentation.screenstates.MovieDetailsScreenState
import com.yakogdan.mtsteta.presentation.viewmodels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val bundleArgs: MovieDetailsFragmentArgs by navArgs()

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieDetailCv.setContent {
            Scaffold(bottomBar = {
                NavigationBar()
            }, content = {
                Text(modifier = Modifier.padding(it), text = "test")
            })
        }
        bundleArgs.movieCard?.let { movieCard ->
            val movieId = movieCard.id
            viewModel.apply {
                getMovieDetailsFromApi(movieId)
                getMovieActorsFromApi(movieId)
                lifecycleScope.launch {
                    movieDetailsStateFLow.collect { state ->
                        processMovieDetailsState(state, movieCard)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.movieActorsStateFlow.collect { state ->
                getMovieActorsFromApi(state)
            }
        }
    }

    @Composable
    fun NavigationBar() {
        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf("Songs", "Artists", "Playlists")

        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index })
            }
        }
    }

    private fun getMovieActorsFromApi(state: MovieActorsScreenState) {
        when (state) {
            MovieActorsScreenState.Error -> {
                Toast.makeText(context, getString(R.string.loading_error), Toast.LENGTH_LONG).show()
            }

            MovieActorsScreenState.Loading -> {
                Toast.makeText(context, getString(R.string.loading), Toast.LENGTH_SHORT).show()
            }

            is MovieActorsScreenState.Result -> {

            }
        }
    }

    private fun processMovieDetailsState(
        state: MovieDetailsScreenState, movieCard: MovieCardDomain
    ) {
        when (state) {
            MovieDetailsScreenState.Error -> {
                Toast.makeText(context, getString(R.string.loading_error), Toast.LENGTH_LONG).show()
            }

            MovieDetailsScreenState.Loading -> {
                Toast.makeText(context, getString(R.string.loading), Toast.LENGTH_SHORT).show()
            }

            is MovieDetailsScreenState.Result -> {

            }
        }
    }
}