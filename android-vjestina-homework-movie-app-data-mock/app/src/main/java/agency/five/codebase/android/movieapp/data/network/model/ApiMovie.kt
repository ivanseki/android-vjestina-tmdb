package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovie(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,
) {
    fun toMovie(isFavorite: Boolean) = Movie(
        id = id,
        title = title,
        overview = overview,
        imageUrl = "$BASE_IMAGE_URL/$posterPath",
        isFavorite = isFavorite
    )
}