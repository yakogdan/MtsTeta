package com.yakogdan.mtsteta.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.mtsteta.R

@Composable
fun MovieDetailsScreen(movieCard: MovieCardDomain, movieActors: List<MovieActorDomain>) {
    Column {
        AsyncImage(
            modifier = Modifier.height(230.dp),
            model = movieCard.posterPath,
            contentDescription = stringResource(id = R.string.movie_poster),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.padding(10.dp))

        val movieGenreListTest = listOf(
            MovieGenreDomain(1L, "комедии"),
            MovieGenreDomain(2L, "боевики"),
            MovieGenreDomain(3L, "драмы"),
            MovieGenreDomain(4L, "мелодрамы"),
            MovieGenreDomain(5L, "артхаус")
        )
        LazyRow {
            items(movieGenreListTest, key = { it.id }) {
                MovieGenreItem(movieGenre = it)
            }
        }

        Row {
            Column {
                Text(text = "Гнев человеческий")
                Text(text = "ratingBar")
                Text(text = "22.2.2023")
            }
            Text(
                modifier = Modifier
                    .padding(top = 2.dp, start = 2.dp)
                    .border(BorderStroke(1.dp, Color.Black), CircleShape)
                    .padding(5.dp),
                text = getAgeRestriction(movieCard),
                fontSize = 8.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = stringResource(id = R.string.movie_description))

        Spacer(modifier = Modifier.padding(10.dp))

        val movieActorsListTest = listOf(
            MovieActorDomain(
                name = "Test Name1",
                profilePath = "testPath1"
            ),
            MovieActorDomain(
                name = "Test Name2",
                profilePath = "testPath2"
            )
        )
        LazyRow {
            items(movieActorsListTest, key = { it.name }) {
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
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    val movieCard = MovieCardDomain(
        id = 1L,
        title = "test title 1",
        description = "test desc 1",
        voteAverage = 5.0,
        adult = true,
        posterPath = "dfgh"
    )

    val movieActors = listOf(
        MovieActorDomain(
            name = "Test Name1",
            profilePath = "testPath1"
        ),
        MovieActorDomain(
            name = "Test Name2",
            profilePath = "testPath2"
        )
    )
    Box(modifier = Modifier.background(Color.White)) {
        MovieDetailsScreen(movieCard = movieCard, movieActors = movieActors)
    }
}

private fun getAgeRestriction(movieCard: MovieCardDomain): String {
    return if (movieCard.adult) {
        "18+"
    } else {
        "12+"
    }
}