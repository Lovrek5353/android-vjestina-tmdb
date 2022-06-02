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
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.R
import com.example.tmdb.data.MovieItemViewState
import com.example.tmdb.ui.theme.FavoriteButton

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    item: MovieItemViewState,
) {
    Box(
        modifier = modifier
            .clickable { onMovieItemClick() }
            .clip(
                RoundedCornerShape(dimensionResource(id = R.dimen.heart_position))
            )
            .size(
                width = dimensionResource(id = R.dimen.movie_card_width_main),
                height = dimensionResource(id = R.dimen.movie_card_height_main),
            )
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${item.poster_path}"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        FavoriteButton(movie = item,
            modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.heart_position),
            top = dimensionResource(id = R.dimen.heart_position)
            )
        )


    }
}


