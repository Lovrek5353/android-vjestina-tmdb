package com.example.tmdb.screens

import android.widget.Space
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle.Companion.values
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Blue
import com.example.tmdb.ui.theme.Grey
import com.example.tmdb.screens.BackPressHandler
import com.example.tmdb.Composables.StarButton
import com.example.tmdb.data.HomeViewModel
import com.example.tmdb.ui.theme.Purple200

@Composable
fun DetailsScreen() {
/*    val DetailsModule= module{
        viewModel{
            DetailsViewModel(
            TODO
            )
        }
    }*/
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            TopAppBar(
                modifier = Modifier.background(Blue)
            ) {
                IconButton(onClick = { Router.navigateTo(Screen.HomeScreen) })
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
                    painter = painterResource(id = R.drawable.iron_man_1),
                    contentDescription = "Movie picture",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Column {
                    Spacer(modifier = Modifier.height(100.dp))
                    CircularProgressIndicator(progress = 0.75f)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Iron Man (2008)", color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "05/02/2008(US)", color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Action, Science Fiction, Adventure  2h 6m", color = Color.White)
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
                    Text(text = stringResource(id = R.string.descp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Row() {
                        ActorsList()
                        ActorsList()
                        ActorsList()
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow() {
                        item {
                            CrewList()
                        }
                        item {
                            CrewList()
                        }
                        item {
                            CrewList()
                        }
                        item {
                            CrewList()
                        }
                    }
                }
            }
        }
    }
    BackPressHandler(onBackPressed = { Router.navigateTo(Screen.HomeScreen) })
}

@Composable
fun ActorsList() {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = "John Doe")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Actor")
    }
}

@Composable
fun CrewList() {
        Column (
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .width(100.dp)
                ) {
            Image(
                painter = painterResource(id = R.drawable.r_downey_junior),
                contentScale = ContentScale.Inside,
                contentDescription = null,
            )
            Text(text = "Robert Downey Jr.")
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Actor")
        }
    }

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen()
}

