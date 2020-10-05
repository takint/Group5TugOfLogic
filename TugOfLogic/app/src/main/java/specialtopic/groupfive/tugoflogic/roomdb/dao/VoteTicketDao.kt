package specialtopic.groupfive.tugoflogic.roomdb.dao

import androidx.room.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.VoteTicket

@Dao
interface VoteTicketDao {
    @Query("SELECT * from vote_tickets")
    fun getAll(): List<VoteTicket>

    @Query("SELECT * from vote_tickets where id = :id")
    fun getById(id: Int): List<VoteTicket>

    @Update
    fun updateVote(vote: VoteTicket)

    @Delete
    fun deleteVote(vote: VoteTicket)

    @Insert
    suspend fun insertVote(vote: VoteTicket)

    @Insert
    suspend fun insertVotes(votes: List<VoteTicket>)

    @Query("DELETE from vote_tickets")
    suspend fun deleteAll()
}