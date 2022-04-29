package com.example.tmdb.Composables

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
fun StarButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClicked: (Boolean) -> Unit = {}
) {
    Image(
        painter = painterResource(id = R.drawable.star),
        contentDescription = "Favorite Button",
        modifier = modifier
            .clickable { onClicked.invoke(!isSelected) }
            .size(dimensionResource(id = R.dimen.heart_button_size))
            .background(Color.LightGray.copy(alpha = 0.8f), CircleShape)
            .padding(dimensionResource(id = R.dimen.small_spacing))

    )
}

@Preview(showBackground = true)
@Composable
fun StarButtonPreview() {
    StarButton()
}