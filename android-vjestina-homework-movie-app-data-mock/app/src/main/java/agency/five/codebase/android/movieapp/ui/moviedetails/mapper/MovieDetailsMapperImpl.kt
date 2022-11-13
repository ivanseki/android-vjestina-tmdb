package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.ActorViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : IMovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = movieDetails.crew.map {
                crewman -> CrewmanViewState(
                    id = crewman.id,
                    name = crewman.name,
                    job = crewman.job
                )},
            cast = movieDetails.cast.map {
                actor ->  ActorViewState(
                    id = actor.id,
                    name = actor.name,
                    character = actor.character,
                    imageUrl = actor.imageUrl
                )
            }
        )
    }

    override fun toCrewmanViewState(crewman: Crewman): CrewmanViewState {
        return CrewmanViewState(
            crewman.id,
            crewman.name,
            crewman.job
        )
    }

    override fun toActorViewState(actor: Actor): ActorViewState {
        return ActorViewState(
            actor.id,
            actor.name,
            actor.character,
            actor.imageUrl
        )
    }
}