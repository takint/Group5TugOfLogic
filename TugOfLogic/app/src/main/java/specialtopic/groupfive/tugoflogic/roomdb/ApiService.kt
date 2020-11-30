package specialtopic.groupfive.tugoflogic.roomdb

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
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
    @PUT("/update-main-claim/{mcId}")
    fun updateMainClaim(@Path("mcId") mainClaimId: Int, @Body mainClaim: MainClaim): Call<MainClaim>

    @DELETE("/delete-main-claim/{mcId}")
    fun deleteMainClaim(@Path("mcId") mainClaimId: Int): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun login(@Body tugGame: User): Call<User>
}