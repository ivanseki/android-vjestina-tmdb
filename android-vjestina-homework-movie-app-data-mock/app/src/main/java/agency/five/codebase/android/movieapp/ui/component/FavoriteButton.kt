package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    onClick: () -> Unit,
) {
    Image(
        painter = painterResource(id = if(isFavourite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined),
        contentDescription = null,
        modifier = modifier
            .clickable { onClick() }
            .background(Blue, CircleShape)
            .wrapContentSize()
    )
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    val favouriteButtonModifier = Modifier
        .padding(2.dp)
        .size(30.dp)

    FavoriteButton(modifier = favouriteButtonModifier, isFavourite = true, onClick = { })
}