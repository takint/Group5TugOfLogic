package specialtopic.groupfive.tugoflogic.roomdb

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import specialtopic.groupfive.tugoflogic.WEB_SERVICE_URL
import specialtopic.groupfive.tugoflogic.roomdb.entities.*
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper

class DataRepository(app: Application) {
    // https://developer.android.com/reference/androidx/lifecycle/MutableLiveData.html
    private val mainClaimData = MutableLiveData<List<MainClaim>>()
    private val ripData = MutableLiveData<List<ReasonInPlay>>()
    private val gameData = MutableLiveData<List<TugGame>>()
    private val userData = MutableLiveData<List<User>>()
    private val voteData = MutableLiveData<List<VoteTicket>>()

    private val mainClaimDao = LogicTugRoomDatabase.getDatabase(app).mainClaimDao()
    private val reasonInPlayDao = LogicTugRoomDatabase.getDatabase(app).reasonInPlayDao()
    private val gameDao = LogicTugRoomDatabase.getDatabase(app).gameDao()
    private val userDao = LogicTugRoomDatabase.getDatabase(app).userDao()
    private val voteTicketDao = LogicTugRoomDatabase.getDatabase(app).voteTicketDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            // get data from SQLite
//            var lstMc = mainClaimDao.getAll()
            var lstRip = reasonInPlayDao.getAll()
            var lstGame = gameDao.getAll()
            //var lstUser = userDao.getAll()
            var lstVote = voteTicketDao.getAll()

            getUsersFromService(app)
            getMCsFromService(app)

//            if (lstUser.isEmpty()) {
//                lstUser = seedUsers()
//                userDao.insertUsers(lstUser)
//                getUsersFromService(app)
//            }

            if (lstGame.isEmpty()) {
                lstGame = seedGames()
                gameDao.insertGames(lstGame)
            }

//            if (lstMc.isEmpty()) {
//                lstMc = seedMainClaim()
//                mainClaimDao.insertMainClaims(lstMc)
//            }

            if (lstRip.isEmpty()) {
                lstRip = seedRips()
                reasonInPlayDao.insertRips(lstRip)
            }

//            userData.postValue(lstUser)
            gameData.postValue(lstGame)
//            mainClaimData.postValue(lstMc)
            ripData.postValue(lstRip)
            voteData.postValue(lstVote)
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

    @WorkerThread
    suspend fun getUsersFromService(app: Application){
        if(NetworkHelper.isNetworkConnected(app)){
            Log.i("Data Repository", "Calling web service")

            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val serviceData = service.getUserData().body() ?: emptyList()

            Log.i("Main DataRepository","$serviceData")
            userData.postValue(serviceData)
        }
    }

    @WorkerThread
    suspend fun getMCsFromService(app: Application){
        if(NetworkHelper.isNetworkConnected(app)){
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val serviceData = service.getMainClaimData().body() ?: emptyList()

            Log.i("Main DataRepository","$serviceData")
            mainClaimData.postValue(serviceData)
        }
    }

    fun readyCheck(app: Application){
        CoroutineScope(Dispatchers.IO).launch {
            getUsersFromService(app);
        }
    }

    fun getGamesData(): MutableLiveData<List<TugGame>> {
        return gameData
    }
}