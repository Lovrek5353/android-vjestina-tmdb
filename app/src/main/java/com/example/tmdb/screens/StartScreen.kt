package com.example.tmdb.screens
//Svi komentari su razrijeseni osim uredivanja
//Pitaj vezano za FlowRow i StartScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.R
import com.example.tmdb.ui.theme.Blue

@Composable
fun StartScreen(){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
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
                                onClick = {Router.navigateTo(Screen.Favorites) },
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
        },
        content = {
            MainScreen()
        }
    )
}

@Preview
@Composable
fun StartScreenPreview(){
    StartScreen()
}