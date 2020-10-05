package specialtopic.groupfive.tugoflogic.roomdb.dao

import androidx.room.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaimWithVotes

@Dao
interface MainClaimDao {
    @Query("SELECT * from main_claims")
    fun getAll(): List<MainClaim>

    @Query("SELECT * from main_claims where id = :id")
    fun getById(id: Int): List<MainClaim>

    @Transaction
    @Query("SELECT * FROM main_claims")
    fun getMainClaimsWithVotes(): List<MainClaimWithVotes>

    @Update
    fun updateMainClaim(mainClaim: MainClaim)

    @Delete
    fun deleteMainClaim(mainClaim: MainClaim)

    @Insert
    suspend fun insertMainClaim(mainClaim: MainClaim)

    @Insert
    suspend fun insertMainClaims(rips: List<MainClaim>)

    @Query("DELETE from main_claims")
    suspend fun deleteAll()
}