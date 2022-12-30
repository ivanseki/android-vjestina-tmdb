package agency.five.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favoriteMovies")
    fun getFavorites(): Flow<List<DbFavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteMovie: DbFavoriteMovie)

    @Query("DELETE FROM favoriteMovies WHERE id = :movieId")
    fun deleteFavorite(movieId: Int)
}