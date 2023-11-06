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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.presentation.ui.items.MovieGenreItem

@Composable
fun MovieDetailsScreen(movieDetails: MovieDetailsDomain, movieActors: List<MovieActorDomain>) {
    Column {
        Poster(movieDetails)

        Spacer(modifier = Modifier.padding(10.dp))

        Genres(movieDetails)

        Spacer(modifier = Modifier.padding(10.dp))

        MovieInfo(movieDetails)

        Spacer(modifier = Modifier.padding(10.dp))

        Description(movieDetails)

        Spacer(modifier = Modifier.padding(10.dp))

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
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = movieDetails.title)
            Text(text = "ratingBar")
            Text(text = movieDetails.releaseDate)
        }
        Text(
            modifier = Modifier
                .padding(top = 2.dp, start = 2.dp)
                .border(BorderStroke(1.dp, Color.Black), CircleShape)
                .padding(5.dp),
            text = getAgeRestriction(movieDetails),
            fontSize = 8.sp,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
        )
    }
}

@Composable
private fun Description(movieDetails: MovieDetailsDomain) {
    Text(text = movieDetails.description)
}

@Composable
private fun Actors(movieActors: List<MovieActorDomain>) {
    LazyRow {
        items(movieActors, key = { it.name }) {
            Column(Modifier.background(Color.Yellow)) {
                AsyncImage(
                    modifier = Modifier.height(196.dp),

                    // TODO: Поправить movieActors[0].profilePath
                    model = stringResource(R.string.beginning_link_actors) + movieActors[0].profilePath,
                    contentDescription = stringResource(id = R.string.actor_photo),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = it.name)
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    val movieGenres = listOf(
        MovieGenreDomain(1L, "комедии"),
        MovieGenreDomain(2L, "боевики"),
        MovieGenreDomain(3L, "драмы"),
        MovieGenreDomain(4L, "мелодрамы"),
        MovieGenreDomain(5L, "артхаус")
    )
    val movieDetails = MovieDetailsDomain(
        id = 1L,
        title = "Гнев человеческий",
        description = stringResource(id = R.string.movie_description),
        adult = true,
        genres = movieGenres,
        posterPath = "dfgh",
        releaseDate = "22.4.2021",
        voteAverage = 5.0
    )

    val movieActors = listOf(
        MovieActorDomain(
            name = "Джейсон Стэйтем", profilePath = "testPath1"
        ), MovieActorDomain(
            name = "Холт Маккэллани", profilePath = "testPath2"
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