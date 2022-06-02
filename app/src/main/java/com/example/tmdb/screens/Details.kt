package com.example.tmdb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.tmdb.Composables.StarButton
import com.example.tmdb.R
import com.example.tmdb.data.DetailsViewModel
import com.example.tmdb.data.MovieDetails.CastMember
import com.example.tmdb.data.MovieDetails.CrewMember
import com.example.tmdb.ui.theme.Blue
import com.example.tmdb.ui.theme.Grey

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, movieId: Int?) {
    val details = viewModel.movieDetailsFlow.collectAsState().value
    val credits = viewModel.movieCreditsFlow.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Blue)
            ) {
                IconButton(onClick = { Router.navigateTo(Screen.StartScreen(StartScreenTab.HomeTab)) })
                {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
                }
                Image(
                    painter = painterResource(id = R.drawable.tmdb_sign_3x),
                    contentDescription = "Movie picture",
                    alignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .background(color = Grey)
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.movie_card_height_details))
                )
                {
                    Image(
                        painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${details.posterPath}"),
                        contentDescription = "Movie picture",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Column {
                        Spacer(modifier = Modifier.height(100.dp))
                        CircularProgressIndicator(progress = details.score)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = details.originalTitle, color = White)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = details.releaseDate, color = White)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${details.genres.joinToString { it.name }} ${details.runtime}min",
                            color = White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        StarButton()
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .background(White)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Overview")
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = details.overview)
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyRow() {
                            items(credits.crew.size) {
                                for (crewMember in credits.crew) {
                                    ActorsList(crewMember = crewMember)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
            item {
                LazyRow() {
                    items(credits.cast.size) {
                        for (castMember in credits.cast) {
                            CrewList(castMember)
                        }
                    }
                }
            }
        }
    }
    BackPressHandler(onBackPressed = { Router.navigateTo(Screen.StartScreen(StartScreenTab.HomeTab)) })
}

@Composable
fun ActorsList(crewMember: CrewMember) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = crewMember.name)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = crewMember.job)
    }
}

@Composable
fun CrewList(castMember: CastMember) {
    Column(

    ) {
        val painter =
            rememberImagePainter(data = "https://image.tmdb.org/t/p/w500/${castMember.profilePath}")
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.cast_card_width),
                    height = dimensionResource(id = R.dimen.cast_card_height)
                )
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.heart_position))),
        )
        Surface(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.heart_position))
                .width(dimensionResource(id = R.dimen.cast_card_width))
        ) {
            Column {
                Text(
                    text = castMember.name,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.heart_position)),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = castMember.characterName,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.heart_position))
                )
            }
        }
    }
}




