package com.example.tmdb.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tmdb.R
import com.example.tmdb.screens.Router
import com.example.tmdb.screens.Screen
import com.example.tmdb.ui.theme.FavoriteButton

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onMovieItemClick: (MovieItemViewState) -> Unit = {},
    item: MovieItemViewState
) {
    Box(
        modifier = modifier
            .clickable { Router.navigateTo(Screen.Details) }
            .clip(
                RoundedCornerShape(dimensionResource(id = R.dimen.heart_position))
            )
            .size(
                width = dimensionResource(id = R.dimen.movie_card_width_main),
                height = dimensionResource(id = R.dimen.movie_card_height_main),
            )

    ) {
        Image(
            painter = painterResource(id = R.drawable.iron_man_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        FavoriteButton(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.heart_position),
                top = dimensionResource(id = R.dimen.heart_position)
            )
        )
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(
        modifier = Modifier,
        item = MovieItemViewState(
            id = 1,
            title = "Iron Man",
            overview = "Overview",
            imageUrl = "R.drawable.iron_man_1"
        )
    )
}

data class MovieItemViewState(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String
)