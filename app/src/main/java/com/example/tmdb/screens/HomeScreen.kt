package com.example.tmdb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.Composables.MovieCard
import com.example.tmdb.Composables.MovieItemViewState
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Blue
import com.example.tmdb.ui.theme.Grey


@Composable
fun MainMovieList(
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
                modifier = Modifier
                    .size(
                        width = dimensionResource(id = R.dimen.movie_card_width_main),
                        height = dimensionResource(id = R.dimen.movie_card_height_main)
                    )
                    .padding(10.dp),
                onMovieItemClick = { onMovieItemClick(it) })
        }
    }
}

@Composable
fun MainScreen() {
    var movies by remember {
        mutableStateOf(
            listOf(
                MovieItemViewState(
                    id = 1,
                    title = "Iron Man 1",
                    overview = "Overview",
                    imageUrl = "R.drawable.iron_man_1"
                ),
                MovieItemViewState(
                    id = 2,
                    title = "Iron Man 1",
                    overview = "Overview",
                    imageUrl = "R.drawable.iron_man_1"
                ),
                MovieItemViewState(
                    id = 3,
                    title = "Iron Man 1",
                    overview = "Overview",
                    imageUrl = "R.drawable.iron_man_1"
                )
            )
        )
    }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                Modifier.background(Blue)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tmdb_sign),
                    contentDescription = "Movie picture",
                    alignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .scale(3F)
                )
            }
        },
        bottomBar = {
            BottomAppBar() {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .alignByBaseline()
                            .padding(start = 100.dp)
                    ) {
                        Column() {
                            IconButton(
                                onClick = { Router.navigateTo(Screen.HomeScreen) },
                                modifier = Modifier.then(Modifier.size(25.dp))
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home button"
                                )
                            }
                            Text(text = "Home", textAlign = TextAlign.Center)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                            .padding(start = 100.dp)
                            .fillMaxWidth()
                    ) {
                        Column() {
                            IconButton(
                                onClick = { Router.navigateTo(Screen.Favorites) },
                                modifier = Modifier
                                    .then(Modifier.size(25.dp))
                                    .fillMaxWidth()
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                                )
                            }
                            Text(text = "Favorites", textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }

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
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Streaming")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "On TV")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "For Rent")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "In theaters")
                    }
                }

            }
            item {
                if (movies.isNotEmpty()) {
                    MainMovieList(
                        modifier = Modifier,
                        onMovieItemClick = { Router.navigateTo(Screen.Details) },
                        MovieItems = movies,
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.free))
                Spacer(modifier = Modifier.height(5.dp))
                Row() {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Movies")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "TV")
                    }
                }
            }
            item {
                if (movies.isNotEmpty()) {
                    MainMovieList(
                        modifier = Modifier,
                        onMovieItemClick = { Router.navigateTo(Screen.Details) },
                        MovieItems = movies,
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.trending))
                Spacer(modifier = Modifier.height(5.dp))
                Row() {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Movies")
                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "TV")
                    }
                }
            }
            item {
                if (movies.isNotEmpty()) {
                    MainMovieList(
                        modifier = Modifier,
                        onMovieItemClick = { Router.navigateTo(Screen.Details) },
                        MovieItems = movies,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}