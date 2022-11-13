package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.ActorViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewmanViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

interface IMovieDetailsMapper {
    fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState
    fun toCrewmanViewState(crewman: Crewman): CrewmanViewState
    fun toActorViewState(actor: Actor): ActorViewState
}