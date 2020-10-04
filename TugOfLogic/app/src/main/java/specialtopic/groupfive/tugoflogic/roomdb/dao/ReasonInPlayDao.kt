package specialtopic.groupfive.tugoflogic.roomdb.dao

import androidx.room.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlayWithVotes

@Dao
interface ReasonInPlayDao {
    @Query("SELECT * from reason_in_plays")
    fun getAll(): List<ReasonInPlay>

    @Query("SELECT * from reason_in_plays where id = :id")
    fun getById(id: Int): List<ReasonInPlay>

    @Transaction
    @Query("SELECT * FROM reason_in_plays")
    fun getRipWithVotes(): List<ReasonInPlayWithVotes>

    @Update
    fun updateRip(rip: ReasonInPlay)

    @Delete
    fun deleteRip(rip: ReasonInPlay)

    @Insert
    suspend fun insertRip(rip: ReasonInPlay)

    @Insert
    suspend fun insertRips(rips: List<ReasonInPlay>)

    @Query("DELETE from reason_in_plays")
    suspend fun deleteAll()
}