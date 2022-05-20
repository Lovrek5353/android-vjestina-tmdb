package com.example.tmdb.screens

import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tmdb.Composables.StarButton
import com.example.tmdb.R
import com.example.tmdb.data.DetailsViewModel
import com.example.tmdb.data.movieDetails.castMember
import com.example.tmdb.data.movieDetails.crewMember
import com.example.tmdb.ui.theme.Blue
import com.example.tmdb.ui.theme.Grey

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, movieId: Int?) {
    val details= viewModel.movieDetailsFlow.collectAsState().value
    val credits=viewModel.movieCreditsFlow.collectAsState().value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            TopAppBar(
                modifier = Modifier.background(Blue)
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
                    CircularProgressIndicator(progress = details.score*10f)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = details.originalTitle, color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = details.releaseDate, color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "${details.genres.joinToString { it.name }} ${details.runtime}", color = Color.White)
                    //Text(text = "Action, Science Fiction, Adventure  2h 6m", color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    StarButton()
                }
            }
        }
        item{
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Overview")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = details.overview)
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow() {
                        for(crewMember in credits.crew){
                            ActorsList(crewMember = crewMember)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(){
                        items(credits.cast.size){
                            for(castMember in credits.cast){
                                CrewList(castMember)
                            }
                        }
                    }
                }
            }
        }
    }
    BackPressHandler(onBackPressed = { Router.navigateTo(Screen.StartScreen(StartScreenTab.HomeTab)) })
}

@Composable
fun ActorsList(crewMember: crewMember) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text =crewMember.name )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = crewMember.job)
    }
}

@Composable
fun CrewList(castMember: castMember) {
        Column (
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .width(100.dp)
                ) {
            Log.d("debug", castMember.profilePath.toString())
            Image(
                painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${castMember.profilePath}"),
                contentScale = ContentScale.Inside,
                contentDescription = null,
            )
            Text(text = castMember.name)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = castMember.characterName)
        }
    }

/*@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(DetailsViewModel(get()))
}*/

