package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MovieCategoryLabelTextViewState()

data class MovieCategoryAsString(val value: String) : MovieCategoryLabelTextViewState()

data class MovieCategoryAsStringResource(@StringRes val value: Int) : MovieCategoryLabelTextViewState()

data class MovieCategoryLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    item: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .wrapContentWidth()
        .height(22.dp)
    ) {
        Text(
            text = when(item.categoryText){
                is MovieCategoryAsString -> item.categoryText.value;
                is MovieCategoryAsStringResource -> stringResource(id = item.categoryText.value);
            },
            fontSize = 16.sp,
            fontWeight = if(item.isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if(item.isSelected) Color.Black else Color.Gray,
            textDecoration = if(item.isSelected) TextDecoration.Underline else TextDecoration.None,
            modifier = modifier
                .clickable { item.isSelected = item.isSelected.not() }
        )
    }
}

@Preview
@Composable
private fun MovieCategoryLabelPreview() {
    MovieCategoryLabel(MovieCategoryLabelViewState(1,true,MovieCategoryAsString("Sci-Fi")))
}