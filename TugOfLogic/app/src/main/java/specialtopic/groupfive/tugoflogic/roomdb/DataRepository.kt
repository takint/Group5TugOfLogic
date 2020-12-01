package specialtopic.groupfive.tugoflogic.roomdb

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.moshi.MoshiConverterFactory
import specialtopic.groupfive.tugoflogic.roomdb.entities.*
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.util.*

class DataRepository(private val app: Application) {
    // https://developer.android.com/reference/androidx/lifecycle/MutableLiveData.html
    private val mainClaimData = MutableLiveData<List<MainClaim>>()
    private val ripData = MutableLiveData<List<ReasonInPlay>>()
    private val gameRipData = MutableLiveData<List<ReasonInPlay>>()
    private val gameData = MutableLiveData<List<TugGame>>()
    private val gamesHistoryData = MutableLiveData<List<TugGame>>()
    private val userData = MutableLiveData<List<User>>()
    private val voteData = MutableLiveData<List<VoteTicket>>()
    private val mainClaimEnt = MutableLiveData<MainClaim>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            // get data from APIs if needed
        }
    }

    fun getMainClaimData(): MutableLiveData<List<MainClaim>> {
        return mainClaimData
    }

    fun getRipsData(): MutableLiveData<List<ReasonInPlay>> {
        return ripData
    }

    fun getUsersData(): MutableLiveData<List<User>> {
        return userData
    }

    fun getGamesData(): MutableLiveData<List<TugGame>> {
        return gameData
    }

    fun getGameRipData(): MutableLiveData<List<ReasonInPlay>> {
        return gameRipData
    }

    fun getGamesHistoryData(): MutableLiveData<List<TugGame>> {
        return gamesHistoryData
    }

    fun getVotesData(): MutableLiveData<List<VoteTicket>> {
        return voteData
    }

    @WorkerThread
    suspend fun getUsersFromService() {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getUserData().body() ?: emptyList()

                userData.postValue(serviceData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    suspend fun getUsersInGame(gameId: Int) {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getUserInGame(gameId).body() ?: emptyList()

                userData.postValue(serviceData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    suspend fun getMCsFromService() {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getMainClaimData().body() ?: emptyList()

                mainClaimData.postValue(serviceData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    suspend fun getMainClaimOnGame(gameId: Int) {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getMainClaimOnGame(gameId).body() ?: emptyList()

                mainClaimData.postValue(serviceData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    suspend fun getGamesFromService() {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {

                val moshi = Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getGameData().body() ?: emptyList()
                gameData.postValue(serviceData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    suspend fun getGamesHistoryFromService() {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {

                val moshi = Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getGamesHistoryData().body() ?: emptyList()
                gamesHistoryData.postValue(serviceData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    suspend fun getGameByIdFromService(
        gameId: Int,
        onComplete: (TugGame?) -> Unit
    ) {
        try {
            if (NetworkHelper.isNetworkConnected(app)) {
                val moshi = Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val gameData = service.getGameById(gameId).body()
                onComplete(gameData)
            }
        } catch (ex: Exception) {
            Log.d("error", ex.message.toString())
        }
    }

    @WorkerThread
    fun createNewGame(newGame: TugGame) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val moshi = Moshi.Builder()
                        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                        .build()
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val newGameCall = service.addGame(newGame)
                    newGameCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    fun addNewUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val newUserCall = service.addUser(user)
                    newUserCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    fun addNewMainClaim(newMainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val moshi = Moshi.Builder().build()
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val newMainClaimCall = service.addNewMainClaim(newMainClaim)
                    newMainClaimCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    fun updateMainClaim(mainClaimId: Int, updatedMainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val moshi = Moshi.Builder().build()
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val updateMainClaimCall = service.updateMainClaim(mainClaimId, updatedMainClaim)
                    updateMainClaimCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    fun deleteMainClaim(mainClaimId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val deleteMainClaimCall = service.deleteMainClaim(mainClaimId)
                    deleteMainClaimCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }

        }
    }

    @WorkerThread
    suspend fun getRiPData() {
        if (NetworkHelper.isNetworkConnected(app)) {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getRiPData().body() ?: emptyList()
                gameRipData.postValue(serviceData)
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    suspend fun getRiPDataByUser(username: String) {
        if (NetworkHelper.isNetworkConnected(app)) {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val serviceData = service.getRiPDataByUser(username).body() ?: emptyList()
                ripData.postValue(serviceData)
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    fun addNewRiP(newRip: ReasonInPlay) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val newRipCall = service.addNewRiP(newRip)
                    newRipCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    fun deleteRiP(deletedRip: ReasonInPlay) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (NetworkHelper.isNetworkConnected(app)) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                        .build()
                    val service = retrofit.create(ApiService::class.java)
                    val deleteRipCall = service.deleteRiP(deletedRip.ripId)
                    deleteRipCall.await()
                }
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }
}