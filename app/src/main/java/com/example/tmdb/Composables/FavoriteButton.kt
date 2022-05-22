package com.example.tmdb.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tmdb.R

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClicked: (Boolean) -> Unit = {}
) {
    Image(
        painter = painterResource(id = if (isSelected) R.drawable.favorite else R.drawable.notfavorite),
        contentDescription = "Favorite Button",
        modifier = modifier
            .clickable { onClicked.invoke(!isSelected) }
            .size(dimensionResource(id = R.dimen.heart_button_size))
            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
            .padding(dimensionResource(id = R.dimen.small_spacing))

    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton()
}