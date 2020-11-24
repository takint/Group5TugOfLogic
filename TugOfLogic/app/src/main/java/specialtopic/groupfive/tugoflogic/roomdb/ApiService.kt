package specialtopic.groupfive.tugoflogic.roomdb

import retrofit2.Response
import retrofit2.http.GET
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.User

interface ApiService {
    @GET("/users")
    suspend fun getUserData(): Response<List<User>>

    @GET("/main-claims")
    suspend fun getMainClaimData(): Response<List<MainClaim>>
}