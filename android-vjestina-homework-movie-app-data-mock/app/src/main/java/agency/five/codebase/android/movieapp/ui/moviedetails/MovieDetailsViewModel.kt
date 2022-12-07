package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    movieDetailsMapper: MovieDetailsMapper,
    movieId: Int,
) : ViewModel() {

    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        movieRepository
            .movieDetails(movieId)
            .map { movies -> movieDetailsMapper.toMovieDetailsViewState(movies) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieDetailsViewState(
                    id = 1,
                    imageUrl = "",
                    voteAverage = 0F,
                    title = "",
                    overview = "",
                    isFavorite = false,
                    crew = emptyList(),
                    cast = emptyList()
                )
            )

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}