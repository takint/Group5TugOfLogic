package specialtopic.groupfive.tugoflogic.roomdb

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.User

interface ApiService {
    @GET("/users")
    suspend fun getUserData(): Response<List<User>>

    @GET("/main-claims")
    suspend fun getMainClaimData(): Response<List<MainClaim>>

    @GET("/games")
    suspend fun getGameData(): Response<List<TugGame>>

    @Headers("Content-Type: application/json")
    @POST("/add-game")
    fun addGame(@Body tugGame: TugGame): Call<TugGame>

    @Headers("Content-Type: application/json")
    @POST("/add-main-claim")
    fun addNewMainClaim(@Body mainClaim: MainClaim): Call<MainClaim>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body tugGame: User): Call<User>
}