package specialtopic.groupfive.tugoflogic.roomdb

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import specialtopic.groupfive.tugoflogic.roomdb.dao.MainClaimDao
import specialtopic.groupfive.tugoflogic.roomdb.entities.*

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
            // get data from SQLite
            var lstMc = mainClaimDao.getAll()
            var lstRip = reasonInPlayDao.getAll()
            var lstGame = gameDao.getAll()
            var lstUser = userDao.getAll()
            var lstVote = voteTicketDao.getAll()

            if (lstUser.isEmpty()) {
                lstUser = seedUsers()
                userDao.insertUsers(lstUser)
            }

            if (lstGame.isEmpty()) {
                lstGame = seedGames()
                gameDao.insertGames(lstGame)
            }

            if (lstMc.isEmpty()) {
                lstMc = seedMainClaim()
                mainClaimDao.insertMainClaims(lstMc)
            }

            if (lstRip.isEmpty()) {
                lstRip = seedRips()
                reasonInPlayDao.insertRips(lstRip)
            }

            userData.postValue(lstUser)
            gameData.postValue(lstGame)
            mainClaimData.postValue(lstMc)
            ripData.postValue(lstRip)
            voteData.postValue(lstVote)
        }
    }

    fun addOrUpdateMainClaimDao(mainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
            if (mainClaim.mainClaimId > 0) {
                mainClaimDao.updateMainClaim(mainClaim)
            } else {
                mainClaimDao.insertMainClaim(mainClaim)
            }
        }
    }

    fun deleteMainClaimDao(mainClaim: MainClaim) {
        CoroutineScope(Dispatchers.IO).launch {
            mainClaimDao.deleteMainClaim(mainClaim)
        }
    }

    fun getMainClaimById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            var mainClaim: List<MainClaim> = mainClaimDao.getById(id)
            mainClaimEnt.postValue(mainClaim[0])
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
}