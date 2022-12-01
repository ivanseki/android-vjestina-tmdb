package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val id: Int,
    val name: String,
    val character: String,
    val imageUrl: String?
)

@Composable
fun ActorCard(
    item: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .weight(0.7F),
                contentScale = ContentScale.Crop
            )

            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp)
            )

            Text(
                text = item.character,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(start = 5.dp, top = 0.dp, end = 5.dp, bottom = 5.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()
    val item = ActorCardViewState(
        id = actor.id,
        name = actor.name,
        imageUrl = actor.imageUrl,
        character = actor.character
    )

    val actorCardModifier = Modifier
        .height(209.dp)
        .width(125.dp)

    ActorCard(modifier = actorCardModifier, item = item)
}