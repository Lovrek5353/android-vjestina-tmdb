package com.example.tmdb.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Blue
import com.example.tmdb.Composables.MovieCard
import com.example.tmdb.data.HomeViewModel
import com.example.tmdb.data.MovieItemViewState


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
                onMovieItemClick = { Router.navigateTo(Screen.Details) },
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
fun FavoritesScreen() {
/*    val FavoritesModule= module{
        viewModel{
            HomeViewModel(TODO)
        }
    }*/
    var FavoriteMovies by remember {
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
    val scaffoldStateMain: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldStateMain,
/*       topBar = {
            TopAppBar(
                Modifier.background(Blue)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tmdb_sign_3x),
                    contentDescription = "Movie picture",
                    alignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .alignByBaseline()
                            .padding(start = 100.dp)
                            .clickable { Router.navigateTo(Screen.HomeScreen) }
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
                            .clickable { Router.navigateTo(Screen.Favorites) }
                    ) {
                        Column() {
                            IconButton(
                                onClick = { Router.navigateTo(Screen.HomeScreen) },
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
        }*/
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

            content ={
                items(FavoriteMovies.size){
                    Box(modifier = Modifier.fillMaxHeight(2f)) {
                        if (FavoriteMovies.isNotEmpty()) {
                            MovieList(
                                modifier = Modifier,
                                onMovieItemClick = { Router.navigateTo(Screen.Details) },
                                MovieItems = FavoriteMovies,
                            )
                        }
                    }
                }
            }
        )
       /* LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = stringResource(id = R.string.favorites))
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            item {
                Box(modifier = Modifier.fillMaxHeight(2f)) {
                    if (movies.isNotEmpty()) {
                        MovieList(
                            modifier = Modifier,
                            onMovieItemClick = { Router.navigateTo(Screen.Details) },
                            MovieItems = movies,
                        )
                    }
                }
            }
        }*/
        BackPressHandler(onBackPressed = { Router.navigateTo(Screen.StartScreen) })
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}

