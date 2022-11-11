package agency.five.codebase.android.movieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Blue,
    secondary = Color.Black,
    background = Gray900,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = Gray700,
    onSurface = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = Blue,
    secondary = Color.White,
    background = Gray100,
    onPrimary = Color.White,
    onBackground = Color.Black,
    surface = Gray300,
    onSurface = Color.Black
)

@Composable
fun MovieAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
