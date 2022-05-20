package com.example.tmdb.ui.theme

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tmdb.R
import com.example.tmdb.data.HomeViewModel
import com.example.tmdb.data.MovieItemViewState
import com.example.tmdb.network.HTTPRoutes
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    movie: MovieItemViewState,
    //isSelected: Boolean = false,

) {
    var homeViewModel= getViewModel<HomeViewModel>()
    var isFavorite by remember {
        mutableStateOf(movie.isFavorite)
    }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            movie.isFavorite = !movie.isFavorite
            if(isFavorite){
                homeViewModel.addToFavorite(movie)
            }
            else{
                homeViewModel.removeFromFavorite(movie)
            }
        }
    )
    {
        Icon(
            painter = painterResource(id = if (isFavorite) R.drawable.favorite else R.drawable.notfavorite),
            contentDescription = "Favorite Button",
        )
    }
/*   Image(
        painter = painterResource(id = if (isSelected) R.drawable.favorite else R.drawable.notfavorite),
        contentDescription = "Favorite Button",
        modifier = modifier
            .clickable { onClicked.invoke(movie, !movie.isFavorite) }
            .size(dimensionResource(id = R.dimen.heart_button_size))
            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
            .padding(dimensionResource(id = R.dimen.small_spacing))
    )*/

}



@Preview(showBackground = true)
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton(
        movie= MovieItemViewState(
            id = 1,
            title = "Iron Man",
            overview = "Overview",
            poster_path = HTTPRoutes.baseImageUrl,
            isFavorite=false,
        ),
    )
}