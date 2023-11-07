package com.yakogdan.mtsteta.presentation.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.mtsteta.R
import com.yakogdan.mtsteta.presentation.ui.theme.bold

@Composable
fun ActorItem(actor: MovieActorDomain) {
    Column {
        AsyncImage(
            modifier = Modifier.size(width = 150.dp, height = 196.dp),
            model = stringResource(R.string.beginning_link_actors) + actor.profilePath,
            contentDescription = stringResource(id = R.string.actor_photo),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(text = actor.name, fontSize = 12.sp, style = bold)
    }
}

@Preview
@Composable
fun ActorItemPreview() {
    val actor = MovieActorDomain(name = "Джейсон Стэйтем", profilePath = "test")
    Box(modifier = Modifier.background(Color.White)) {
        ActorItem(actor = actor)
    }
}