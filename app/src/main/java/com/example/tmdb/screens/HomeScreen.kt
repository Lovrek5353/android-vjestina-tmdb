package com.example.tmdb.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.Composables.MovieCard
import com.example.tmdb.R
import com.example.tmdb.data.HomeViewModel
import com.example.tmdb.data.MovieItemViewState
import com.example.tmdb.ui.theme.Grey
import org.koin.androidx.compose.get


@Composable
fun MainMovieList(
    modifier: Modifier = Modifier,
    onMovieItemClick: (MovieItemViewState) -> Unit = {},
    MovieItems: List<MovieItemViewState>,
    viewModel: HomeViewModel
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
                        width = dimensionResource(id = R.dimen.movie_card_width_main),
                        height = dimensionResource(id = R.dimen.movie_card_height_main)
                    )
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun MainScreen(
    viewModel: HomeViewModel
) {
    var popularMovies = viewModel.getPopularMovies().collectAsState(initial = listOf()).value
    var nowPlayingMovies = viewModel.getNowPlayingMovies().collectAsState(initial = listOf()).value
    var upcomingMovies = viewModel.getUpcomingMovies().collectAsState(initial = listOf()).value
    var topRatedMovies = viewModel.getTopRatedMovies().collectAsState(initial = listOf()).value


    var movieCategory by remember { mutableStateOf(0) }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        LazyColumn() {
            item {
                TopAppBar(
                    backgroundColor = Grey,
                    modifier = Modifier
                        .scale(0.75f)
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search icon"
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.whatspopular))
                Spacer(modifier = Modifier.height(5.dp))
                Row() {
                    TextButton(onClick = { movieCategory = 1 }) {
                        Text(text = "Popular")
                    }
                    TextButton(onClick = { movieCategory = 2 }) {
                        Text(text = "Top rated")
                    }
                }
            }

            var moviesToShow = popularMovies
            when (movieCategory) {
                1 -> moviesToShow = popularMovies
                2 -> moviesToShow = topRatedMovies
            }
            item {
                if (moviesToShow.isNotEmpty()) {
                    MainMovieList(
                        modifier = Modifier,
                        onMovieItemClick = { Router.navigateTo(Screen.Details(it.id)) },
                        MovieItems = moviesToShow,
                        viewModel = HomeViewModel(get())
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.free))
                Spacer(modifier = Modifier.height(5.dp))
            }

            var upcomingMovies = upcomingMovies
            item {
                if (upcomingMovies.isNotEmpty()) {
                    MainMovieList(
                        modifier = Modifier,
                        onMovieItemClick = { Router.navigateTo(Screen.Details(it.id)) },
                        MovieItems = upcomingMovies,
                        viewModel = HomeViewModel(get())
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.trending))
                Spacer(modifier = Modifier.height(5.dp))

            }
            var upcomingToWatch = popularMovies
            item {
                if (upcomingToWatch.isNotEmpty()) {
                    MainMovieList(
                        modifier = Modifier,
                        onMovieItemClick = { Router.navigateTo(Screen.Details(it.id)) },
                        MovieItems = upcomingToWatch,
                        viewModel = HomeViewModel(get())
                    )
                }
            }
        }
    }
    BackPressHandler(onBackPressed = { Router.navigateTo(Screen.StartScreen(StartScreenTab.HomeTab)) })
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(HomeViewModel(get()))
}