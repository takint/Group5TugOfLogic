package specialtopic.groupfive.tugoflogic.roomdb.dao

import androidx.room.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.User
import specialtopic.groupfive.tugoflogic.roomdb.entities.UserWithRips
import specialtopic.groupfive.tugoflogic.roomdb.entities.UserWithVotes

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAll(): List<User>

    @Query("SELECT * from users where id = :id")
    fun getById(id: Int): List<User>

    @Transaction
    @Query("SELECT * FROM users")
    fun getUserWithVotes(): List<UserWithVotes>

    @Transaction
    @Query("SELECT * FROM users")
    fun getUserWithRips(): List<UserWithRips>

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Insert
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE from users")
    suspend fun deleteAll()
}