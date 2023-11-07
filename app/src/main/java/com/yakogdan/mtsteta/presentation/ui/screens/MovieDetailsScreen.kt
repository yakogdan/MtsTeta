package com.yakogdan.mtsteta.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.presentation.ui.items.ActorItem
import com.yakogdan.mtsteta.presentation.ui.items.MovieGenreItem
import com.yakogdan.mtsteta.presentation.ui.theme.BlackText
import com.yakogdan.mtsteta.presentation.ui.theme.bold
import com.yakogdan.mtsteta.presentation.ui.theme.regular

@Composable
fun MovieDetailsScreen(movieDetails: MovieDetailsDomain, movieActors: List<MovieActorDomain>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Poster(movieDetails)

        Spacer(modifier = Modifier.padding(top = 32.dp))

        Genres(movieDetails)

        Spacer(modifier = Modifier.padding(top = 14.dp))

        MovieInfo(movieDetails)

        Spacer(modifier = Modifier.padding(top = 20.dp))

        Description(movieDetails)

        Spacer(modifier = Modifier.padding(top = 32.dp))

        Actors(movieActors)
    }
}

@Composable
private fun Poster(movieDetails: MovieDetailsDomain) {
    AsyncImage(
        modifier = Modifier.height(230.dp),
        model = movieDetails.posterPath,
        contentDescription = stringResource(id = R.string.movie_poster),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun Genres(movieDetails: MovieDetailsDomain) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(start = 20.dp)
    ) {
        items(movieDetails.genres, key = { it.id }) {
            MovieGenreItem(movieGenre = it)
        }
    }
}

@Composable
private fun MovieInfo(movieDetails: MovieDetailsDomain) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = movieDetails.title, fontSize = 20.sp, style = bold)
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(text = "ratingBar")
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(text = movieDetails.releaseDate, fontSize = 12.sp, style = regular)
        }
        Text(
            text = getAgeRestriction(movieDetails),
            modifier = Modifier
                .border(BorderStroke(1.dp, BlackText), CircleShape)
                .padding(start = 7.dp, top = 9.dp, end = 7.dp, bottom = 9.dp),
            fontSize = 14.sp,
            style = regular,
        )
    }
}

@Composable
private fun Description(movieDetails: MovieDetailsDomain) {
    Text(
        text = movieDetails.description,
        modifier = Modifier.padding(horizontal = 20.dp),
        fontSize = 14.sp,
        style = regular
    )
}

@Composable
private fun Actors(movieActors: List<MovieActorDomain>) {
    Text(
        text = stringResource(id = R.string.actors),
        modifier = Modifier.padding(start = 20.dp),
        fontSize = 16.sp,
        style = bold
    )

    Spacer(modifier = Modifier.padding(top = 16.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 20.dp)
    ) {
        items(movieActors, key = { it.name }) {
            ActorItem(it)
        }
    }

    Spacer(modifier = Modifier.padding(top = 32.dp))
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    val movieGenres = listOf(
        MovieGenreDomain(1L, "комедии"),
        MovieGenreDomain(2L, "боевики"),
        MovieGenreDomain(3L, "драмы"),
        MovieGenreDomain(4L, "мелодрамы"),
        MovieGenreDomain(5L, "военные"),
        MovieGenreDomain(6L, "фантастика"),
        MovieGenreDomain(7L, "артхаус")
    )
    val movieDetails = MovieDetailsDomain(
        id = 1L,
        title = "Гнев человеческий",
        description = stringResource(id = R.string.movie_description),
        adult = true,
        genres = movieGenres,
        posterPath = "dfgh",
        releaseDate = "22.04.2021",
        voteAverage = 5.0
    )

    val movieActors = listOf(
        MovieActorDomain(
            name = "Джейсон Стэйтем", profilePath = "testPath1"
        ), MovieActorDomain(
            name = "Холт Маккэллани", profilePath = "testPath2"
        ), MovieActorDomain(
            name = "Джош Харнетт", profilePath = "testPath3"
        ), MovieActorDomain(
            name = "Владимир Епифанцев", profilePath = "testPath4"
        )
    )
    Box(modifier = Modifier.background(Color.White)) {
        MovieDetailsScreen(movieDetails = movieDetails, movieActors = movieActors)
    }
}

private fun getAgeRestriction(movieDetails: MovieDetailsDomain): String {
    return if (movieDetails.adult) {
        "18+"
    } else {
        "12+"
    }
}