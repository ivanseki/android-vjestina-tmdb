package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    contentDescription: String = "Favourite button"
) {
    val favourite = remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = if(favourite.value) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined),
        contentDescription = contentDescription,
        modifier = modifier
            .clickable {
                favourite.value = favourite.value.not()
            }
            .size(width = 32.dp, height = 32.dp)
            .background(Blue, CircleShape)
            .padding(top = 8.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
    )
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    FavoriteButton()
}