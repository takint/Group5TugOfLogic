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
import retrofit2.converter.moshi.MoshiConverterFactory
import specialtopic.groupfive.tugoflogic.WEB_SERVICE_URL
import specialtopic.groupfive.tugoflogic.roomdb.entities.*
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.util.*

class DataRepository(app: Application) {
    // https://developer.android.com/reference/androidx/lifecycle/MutableLiveData.html
    private val mainClaimData = MutableLiveData<List<MainClaim>>()
    private val ripData = MutableLiveData<List<ReasonInPlay>>()
    private val gameData = MutableLiveData<List<TugGame>>()
    private val userData = MutableLiveData<List<User>>()
    private val voteData = MutableLiveData<List<VoteTicket>>()

    private val mainClaimEnt = MutableLiveData<MainClaim>()

    private val mainClaimDao = LogicTugRoomDatabase.getDatabase(app).mainClaimDao()
    private val reasonInPlayDao = LogicTugRoomDatabase.getDatabase(app).reasonInPlayDao()
    private val gameDao = LogicTugRoomDatabase.getDatabase(app).gameDao()
    private val userDao = LogicTugRoomDatabase.getDatabase(app).userDao()
    private val voteTicketDao = LogicTugRoomDatabase.getDatabase(app).voteTicketDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            // get data from APIs
            getUsersFromService(app)
            getMCsFromService(app)
            getGamesFromService(app)
        }
    }

    /**
     * Expose the add and update main claim operation from DAO
     * for the UI can use to handle the data from users
     * */
    fun addOrUpdateMainClaimDao(mainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
            if (mainClaim.mainClaimId > 0) {
                mainClaimDao.updateMainClaim(mainClaim)
            } else {
                mainClaimDao.insertMainClaim(mainClaim)
            }
        }
    }

    /**
     * Expose the delete main claim operation from DAO
     * for the UI can use to handle the data from users
     * */
    fun deleteMainClaimDao(mainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
            mainClaimDao.deleteMainClaim(mainClaim)
        }
    }

    /**
     * Get main claim by Id
     * */
    fun getMainClaimById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val mainClaim: List<MainClaim> = mainClaimDao.getById(id)
            mainClaimEnt.postValue(mainClaim[0])
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
                newGameCall.execute()
            }
        }
    }
}