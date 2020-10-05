package specialtopic.groupfive.tugoflogic.roomdb

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import specialtopic.groupfive.tugoflogic.roomdb.entities.*

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
            val lstMc = mainClaimDao.getAll()
            val lstRip = reasonInPlayDao.getAll()
            val lstGame = gameDao.getAll()
            val lstUser = userDao.getAll()
            val lstVote = voteTicketDao.getAll()

            mainClaimData.postValue(lstMc)
            ripData.postValue(lstRip)
            gameData.postValue(lstGame)
            userData.postValue(lstUser)
            voteData.postValue(lstVote)
        }
    }

    fun getMainClaimData(): MutableLiveData<List<MainClaim>> {
        return mainClaimData
    }
}