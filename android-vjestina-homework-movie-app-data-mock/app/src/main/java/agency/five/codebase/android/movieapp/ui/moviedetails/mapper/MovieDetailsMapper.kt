package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

interface MovieDetailsMapper {
    fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState
}