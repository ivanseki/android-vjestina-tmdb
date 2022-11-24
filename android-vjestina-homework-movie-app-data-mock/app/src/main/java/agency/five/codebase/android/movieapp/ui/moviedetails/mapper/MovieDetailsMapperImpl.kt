package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = toCrewmanViewState(movieDetails),
            cast = toActorViewState(movieDetails)
        )
    }

    private fun toCrewmanViewState(movieDetails: MovieDetails) =
        movieDetails.crew.map { crewman ->
            CrewmanViewState(
                id = crewman.id,
                name = crewman.name,
                job = crewman.job
            )
        }

    private fun toActorViewState(movieDetails: MovieDetails) =
        movieDetails.cast.map { actor ->
            ActorCardViewState(
                id = actor.id,
                name = actor.name,
                character = actor.character,
                imageUrl = actor.imageUrl
            )
        }
}