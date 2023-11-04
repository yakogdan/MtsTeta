package com.yakogdan.mtsteta.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
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
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.mtsteta.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCard(
    movieCard: MovieCardDomain,
    onItemClickListener: (MovieCardDomain) -> Unit,
    onItemLongClickListener: (MovieCardDomain) -> Unit
) {
    Card(
        modifier = Modifier
            .size(width = 165.dp, height = 380.dp)
            .combinedClickable(
                onClick = { onItemClickListener },
                onLongClick = { onItemLongClickListener }
            )
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.height(220.dp),
                model = stringResource(R.string.beginning_link_movie_card) + movieCard.posterPath,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
            )

            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = movieCard.title,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold))
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
            )

            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = movieCard.description,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                maxLines = 6
            )

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
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movieCard = MovieCardDomain(
        id = 1L,
        title = stringResource(R.string.wrath_of_man_title),
        description = stringResource(R.string.movie_description),
        voteAverage = 3.0,
        adult = true,
        posterPath = "https://i.ibb.co/0rBBZSs/test-Poster.jpg"
    )
    MovieCard(movieCard = movieCard, onItemClickListener = {}, onItemLongClickListener = {})
}

private fun getAgeRestriction(movieCard: MovieCardDomain): String {
    return if (movieCard.adult) {
        "18+"
    } else {
        "12+"
    }
}