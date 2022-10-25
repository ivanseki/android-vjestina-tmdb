package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    item: CrewItemViewState
) {
    Column() {
        Text(
            text = item.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = item.job,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun CrewItemPreview() {
    val crewMan = MoviesMock.getCrewman()
    val item = CrewItemViewState(
        name = crewMan.name,
        job = crewMan.job
    )
    
    CrewItem(item = item)
}