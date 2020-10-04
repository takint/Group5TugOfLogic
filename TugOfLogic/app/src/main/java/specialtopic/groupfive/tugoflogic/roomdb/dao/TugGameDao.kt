package specialtopic.groupfive.tugoflogic.roomdb.dao

import androidx.room.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGameWithMainClaims

@Dao
interface TugGameDao {
    @Query("SELECT * from games")
    fun getAll(): List<TugGame>

    @Query("SELECT * from games where id = :id")
    fun getById(id: Int): List<TugGame>

    @Transaction
    @Query("SELECT * FROM games")
    fun getGameWithMainClaims(): List<TugGameWithMainClaims>

    @Update
    fun updateGame(game: TugGame)

    @Delete
    fun deleteGame(game: TugGame)

    @Insert
    suspend fun insertGame(game: TugGame)

    @Insert
    suspend fun insertGames(games: List<TugGame>)

    @Query("DELETE from games")
    suspend fun deleteAll()
}