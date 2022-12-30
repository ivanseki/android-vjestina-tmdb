package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.data.database.DbFavoriteMovie
import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieDao: FavoriteMovieDao,
    private val bgDispatcher: CoroutineDispatcher
) : MovieRepository {

    private val moviesByCategory: Map<MovieCategory, Flow<List<Movie>>> = MovieCategory.values()
        .associateWith { movieCategory ->
            flow {
                val movieResponse = when (movieCategory) {
                    MovieCategory.POPULAR_STREAMING -> movieService.fetchPopularMovies()
                    MovieCategory.POPULAR_ON_TV -> movieService.fetchNowPlayingMovies()
                    MovieCategory.POPULAR_FOR_RENT -> movieService.fetchUpcomingMovies()
                    MovieCategory.POPULAR_IN_THEATRES -> movieService.fetchTopRatedMovies()
                    MovieCategory.NOW_PLAYING_MOVIES -> movieService.fetchPopularMovies()
                    MovieCategory.NOW_PLAYING_TV -> movieService.fetchNowPlayingMovies()
                    MovieCategory.UPCOMING_TODAY -> movieService.fetchPopularMovies()
                    MovieCategory.UPCOMING_THIS_WEEK -> movieService.fetchUpcomingMovies()
                }
                emit(movieResponse.movies)
            }.flatMapLatest { apiMovies ->
                movieDao.getFavorites()
                    .map { favoriteMovies ->
                        apiMovies.map { apiMovie ->
                            apiMovie.toMovie(
                                isFavorite = favoriteMovies.any { it.id == apiMovie.id }
                            )
                        }
                    }
            }.shareIn(
                scope = CoroutineScope(bgDispatcher),
                started = SharingStarted.WhileSubscribed(1000L),
                replay = 1
            )
        }

    private val favorites = movieDao.getFavorites().map {
        it.map { dbFavoriteMovie ->
            Movie(
                id = dbFavoriteMovie.id,
                imageUrl = dbFavoriteMovie.posterUrl,
                title = "",
                overview = "",
                isFavorite = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )


    override fun movies(movieCategory: MovieCategory): Flow<List<Movie>> =
        moviesByCategory[movieCategory]!!

    override fun movieDetails(movieId: Int): Flow<MovieDetails> = flow {
        emit(movieService.fetchMovieDetails(movieId) to movieService.fetchMovieCredits(movieId))
    }.flatMapLatest { (apiMovieDetails, apiMovieCredits) ->
        movieDao.getFavorites()
            .map { favoriteMovies ->
                apiMovieDetails.toMovieDetails(
                    isFavorite = favoriteMovies.any { it.id == apiMovieDetails.id },
                    crew = apiMovieCredits.crew.map { crewman -> crewman.toCrewman() },
                    cast = apiMovieCredits.cast.map { actor -> actor.toActor() }
                )
            }
    }.flowOn(bgDispatcher)

    override fun favoriteMovies(): Flow<List<Movie>> = favorites

    override suspend fun addMovieToFavorites(movieId: Int) {
        runBlocking(bgDispatcher) {
            movieDao.insertFavorite(
                favoriteMovie = DbFavoriteMovie(
                    id = movieId,
                    posterUrl = BASE_IMAGE_URL + movieService.fetchMovieDetails(movieId).posterPath
                )
            )
        }
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        runBlocking(bgDispatcher) {
            movieDao.deleteFavorite(movieId)
        }
    }

    private suspend fun findMovie(movieId: Int): Movie? {
        var movie: Movie? = null
        moviesByCategory.values.forEach { value ->
            val movies = value.first()
            movies.forEach {
                if (it.id == movieId)
                    movie = it
            }
        }
        return movie
    }

    override suspend fun toggleFavorite(movieId: Int) {
        runBlocking(bgDispatcher) {
            val movie = findMovie(movieId)
            if (movie?.isFavorite == true) {
                removeMovieFromFavorites(movieId)
            } else {
                addMovieToFavorites(movieId)
            }
        }
    }
}