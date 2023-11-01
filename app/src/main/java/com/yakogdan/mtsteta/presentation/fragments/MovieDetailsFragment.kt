package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
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
                MovieDetailsScreen(it)
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

    @Composable
    fun MovieDetailsScreen(paddingValues: PaddingValues) {
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = stringResource(R.string.popular_now))

            val movieGenreListTest = listOf(
                MovieGenreDomain(1L, "test title 1"),
                MovieGenreDomain(2L, "test title 2"),
                MovieGenreDomain(3L, "test title 3"),
                MovieGenreDomain(4L, "test title 4")
            )

            LazyRow {
                items(movieGenreListTest, key = { it.id }) {
                    Box(Modifier.background(Color.Red)) {
                        Text(text = it.title, fontSize = 30.sp)
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
            val movieCardsListTest = listOf(
                MovieCardDomain(
                    id = 1L,
                    title = "test title 1",
                    description = "test desc 1",
                    voteAverage = 5.0,
                    adult = true,
                    posterPath = "dfgh"
                ),
                MovieCardDomain(
                    id = 2L,
                    title = "test title 2 ",
                    description = "test desc 2",
                    voteAverage = 3.0,
                    adult = true,
                    posterPath = "asdf"
                )
            )

            LazyColumn {
                items(movieCardsListTest, key = { it.id }) {
                    Column(Modifier.background(Color.Yellow)) {
                        Text(text = it.title)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = it.description)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = it.voteAverage.toString())
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}