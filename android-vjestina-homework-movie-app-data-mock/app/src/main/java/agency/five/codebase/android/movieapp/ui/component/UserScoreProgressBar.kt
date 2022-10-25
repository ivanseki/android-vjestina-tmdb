package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScoreProgressBar(
    score: Float,
    modifier: Modifier = Modifier
) {
    Box{
        CircularProgressIndicator(
            progress = score,
            color = Color.Green,
            strokeWidth = 4.dp,
            modifier = modifier
                .width(42.dp)
                .height(42.dp),

        )

        Text(
            text = (score * 10).toString(),
            fontSize = 15.sp,
            modifier = modifier
                .padding(10.dp)
        )
    }
}

@Preview()
@Composable
private fun CircularProgressBarPreview() {
    val score = 0.75f
    UserScoreProgressBar(score = score)
}