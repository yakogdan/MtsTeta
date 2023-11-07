package com.yakogdan.mtsteta.presentation.ui.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.presentation.ui.theme.regular

@Composable
fun MovieGenreItem(movieGenre: MovieGenreDomain) {
    Text(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black), CircleShape)
            .padding(start = 6.dp, top = 3.dp, end = 6.dp, bottom = 4.dp),
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        style = regular,
        text = movieGenre.title
    )
}

@Preview
@Composable
fun MovieGenreItemPreview() {
    val movieGenre = MovieGenreDomain(id = 1L, title = "комедии")
    Box(Modifier.background(Color.White)) {
        MovieGenreItem(movieGenre = movieGenre)
    }
}