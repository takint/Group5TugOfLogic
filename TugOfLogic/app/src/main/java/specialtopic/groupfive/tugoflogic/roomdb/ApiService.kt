package specialtopic.groupfive.tugoflogic.roomdb

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.User

interface ApiService {
    @GET("/users")
    suspend fun getUserData(): Response<List<User>>

    @Headers("Content-Type: application/json")
    @POST("/add-user")
    fun addUser(@Body user: User): Call<User>

    @GET("/main-claims")
    suspend fun getMainClaimData(): Response<List<MainClaim>>

    @GET("/main-claims-on-game/{gameId}")
    suspend fun getMainClaimOnGame(@Path("gameId") gameId: Int): Response<List<MainClaim>>

    @GET("/games")
    suspend fun getGameData(): Response<List<TugGame>>

    @GET("/games?isCurrent=false")
    suspend fun getGamesHistoryData(): Response<List<TugGame>>

    @GET("/games/{gameId}")
    suspend fun getGameById(@Path("gameId") gameId: Int): Response<TugGame>

    @GET("/rips")
    suspend fun getRiPData(): Response<List<ReasonInPlay>>

    @GET("/rips/{username}")
    suspend fun getRiPDataByUser(@Path("username") username: String): Response<List<ReasonInPlay>>

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

    @Headers("Content-Type: application/json")
    @POST("/add-rip")
    fun addNewRiP(@Body rip: ReasonInPlay): Call<ReasonInPlay>

    @Headers("Content-Type: application/json")
    @PUT("/update-rip")
    fun updateRiP(@Body rip: ReasonInPlay): Call<ReasonInPlay>

    @DELETE("/delete-rip/{ripId}")
    fun deleteRiP(@Path("ripId") ripId: Int): Call<Void?>
}