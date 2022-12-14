package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("original_language")
    val original_language: String,

    @SerialName("release_date")
    val release_date: String,

    @SerialName("runtime")
    val runtime: Int,

    @SerialName("vote_average")
    val vote_average: Float
) {
    fun toMovieDetails(isFavorite: Boolean, crew: List<Crewman>, cast: List<Actor>) = MovieDetails(
        movie = Movie(
            id = id,
            title = title,
            overview = overview,
            imageUrl = posterPath,
            isFavorite = isFavorite
        ),
        voteAverage = vote_average,
        releaseDate = release_date,
        language = original_language,
        runtime = runtime,
        crew = crew,
        cast = cast
    )
}