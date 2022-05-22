package com.example.tmdb.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.Composables.MovieCard
import com.example.tmdb.R
import com.example.tmdb.data.FavoritesViewModel
import com.example.tmdb.data.MovieItemViewState
import org.koin.androidx.compose.get

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    onMovieItemClick: (MovieItemViewState) -> Unit = {},
    MovieItems: List<MovieItemViewState>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(15.dp)
    ) {
        items(MovieItems) {
            MovieCard(
                item = it,
                onMovieItemClick = { onMovieItemClick(it) },
                modifier = Modifier
                    .size(
                        width = dimensionResource(id = R.dimen.movie_card_width_favorite),
                        height = dimensionResource(id = R.dimen.movie_card_height_favorite)
                    )
                    .padding(10.dp),
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {
    var FavoriteMovies= viewModel.getFavoriteMovies().collectAsState(initial = mutableListOf()).value

    val scaffoldStateMain: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldStateMain,
    )
    {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(id = R.string.favorites))
            Spacer(modifier = Modifier.height(10.dp))
        }
        LazyVerticalGrid(   //Kako izbrisati ove crte, kada se klikne na njih dobiva se scafford
            cells = GridCells.Adaptive(dimensionResource(id = R.dimen.movie_card_width_main)),
            contentPadding = PaddingValues(20.dp),
            content ={
                items(FavoriteMovies){ item->
                            MovieCard(
                                modifier = Modifier
                                    .padding(5.dp),
                                item=item,
                                onMovieItemClick = ({ Router.navigateTo(Screen.Details(item.id)) }),
                            )
                }
            }
        )
        BackPressHandler(onBackPressed = { Router.navigateTo(Screen.StartScreen(StartScreenTab.HomeTab)) })
    }
}



@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(FavoritesViewModel(get()))
}

