package agency.five.codebase.android.movieapp.mock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object FavoritesDBMock {
    private val favoritesIdsState = MutableStateFlow(setOf<Int>())
    val favoriteIds: StateFlow<Set<Int>> = favoritesIdsState.asStateFlow()
    fun insert(movieId: Int) = favoritesIdsState.update { it + movieId }
    fun delete(movieId: Int) = favoritesIdsState.update { it - movieId }
}