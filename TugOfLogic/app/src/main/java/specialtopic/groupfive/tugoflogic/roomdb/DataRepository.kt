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
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import specialtopic.groupfive.tugoflogic.roomdb.entities.*
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.util.*

class DataRepository(app: Application) {
    // https://developer.android.com/reference/androidx/lifecycle/MutableLiveData.html
    private val mainClaimData = MutableLiveData<List<MainClaim>>()
    private val ripData = MutableLiveData<List<ReasonInPlay>>()
    private val gameRipData = MutableLiveData<List<ReasonInPlay>>()
    private val gameData = MutableLiveData<List<TugGame>>()
    private val gamesHistoryData = MutableLiveData<List<TugGame>>()
    private val userData = MutableLiveData<List<User>>()
    private val voteData = MutableLiveData<List<VoteTicket>>()
    private val selectedGameHistory = MutableLiveData<List<TugGame>>()
    private val mainClaimEnt = MutableLiveData<MainClaim>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            // get data from APIs
            getUsersFromService(app)
            getGamesFromService(app)
            getGamesHistoryFromService(app)
        }
    }
    /**
     * Get main claim data for UI
     * */
    fun getMainClaimData(): MutableLiveData<List<MainClaim>> {
        return mainClaimData
    }

    /**
     * Get reason in play data for UI
     * */
    fun getRipsData(): MutableLiveData<List<ReasonInPlay>> {
        return ripData
    }

    /**
     * Get users for UI
     * */
    fun getUsersData(): MutableLiveData<List<User>> {
        return userData
    }

    /**
     * Get games data for UI
     * */
    fun getGamesData(): MutableLiveData<List<TugGame>> {
        return gameData
    }

    fun getGameRipData(): MutableLiveData<List<ReasonInPlay>> {
        return gameRipData
    }
    /**
     * Get game history data for UI
     * */
    fun getGamesHistoryData(): MutableLiveData<List<TugGame>> {
        return gamesHistoryData
    }

    @WorkerThread
    suspend fun getUsersFromService(app: Application) {
        if (NetworkHelper.isNetworkConnected(app)) {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val serviceData = service.getUserData().body() ?: emptyList()

            Log.i("Main DataRepository", "$serviceData")
            userData.postValue(serviceData)
        }
    }

    @WorkerThread
    suspend fun getMCsFromService(app: Application) {
        if (NetworkHelper.isNetworkConnected(app)) {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val serviceData = service.getMainClaimData().body() ?: emptyList()

            Log.i("Main DataRepository", "$serviceData")
            mainClaimData.postValue(serviceData)
        }
    }

    @WorkerThread
    suspend fun getMainClaimOnGame(app: Application, gameId: Int) {
        if (NetworkHelper.isNetworkConnected(app)) {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val serviceData = service.getMainClaimOnGame(gameId).body() ?: emptyList()

            mainClaimData.postValue(serviceData)
        }
    }

    @WorkerThread
    suspend fun getGamesFromService(app: Application) {
        if (NetworkHelper.isNetworkConnected(app)) {
            try {
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
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    suspend fun getGamesHistoryFromService(app: Application) {
        if (NetworkHelper.isNetworkConnected(app)) {
            try {
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
            } catch (ex: Exception) {
                Log.d("error", ex.message.toString())
            }
        }
    }

    @WorkerThread
    suspend fun getGameByIdFromService(app: Application, gameId: Int, onComplete: (TugGame?) -> Unit) {
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
    }

    fun readyCheck(app: Application) {
        CoroutineScope(Dispatchers.IO).launch {
            getUsersFromService(app);
        }
    }

    @WorkerThread
    fun createNewGame(app: Application, newGame: TugGame) {
        CoroutineScope(Dispatchers.IO).launch {
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
        }
    }

    @WorkerThread
    fun addNewUser(app: Application, user: User){
        CoroutineScope(Dispatchers.IO).launch {
            if (NetworkHelper.isNetworkConnected(app)) {
                val moshi = Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val newUserCall = service.addUser(user)
                newUserCall.execute()
            }
        }
    }

    @WorkerThread
    fun addNewMainClaim(app: Application, newMainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
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
        }
    }

    @WorkerThread
    fun updateMainClaim(app: Application, mainClaimId: Int, updatedMainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
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
        }
    }

    @WorkerThread
    fun deleteMainClaim(app: Application, mainClaimId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val deleteMainClaimCall = service.deleteMainClaim(mainClaimId)
                deleteMainClaimCall.await()
            }
        }
    }

    @WorkerThread
    suspend fun getRiPData(app: Application) {
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
    suspend fun getRiPDataByUser(app: Application, username: String) {
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
    fun addNewRiP(app: Application, newRip: ReasonInPlay) {
        CoroutineScope(Dispatchers.IO).launch {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val newRipCall = service.addNewRiP(newRip)
                newRipCall.await()
            }
        }
    }

    @WorkerThread
    fun deleteRiP(app: Application, deletedRip: ReasonInPlay) {
        CoroutineScope(Dispatchers.IO).launch {
            if (NetworkHelper.isNetworkConnected(app)) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(NetworkHelper.API_ENDPOINT_URL)
                    .build()
                val service = retrofit.create(ApiService::class.java)
                val deleteRipCall = service.deleteRiP(deletedRip.ripId)
                deleteRipCall.await()
            }
        }
    }
}