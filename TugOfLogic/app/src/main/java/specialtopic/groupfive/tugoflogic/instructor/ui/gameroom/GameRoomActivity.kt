package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_game_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.UsersAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.User
import specialtopic.groupfive.tugoflogic.utilities.GAME_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class GameRoomActivity : AppCompatActivity() {
    private lateinit var tugDataRepo: DataRepository
    private lateinit var rvUsers: RecyclerView
    private lateinit var adapter: UsersAdapter

    private var randomGameID = Random.nextInt(100000, 1000000)
    var listUsers = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_room)

        //Set RoomID and title later
        txt_GameRoom_Message.text = getString(R.string.wait_for_people)

        // Init data repository for using on this fragment
        tugDataRepo = DataRepository(application)

        val newGame = TugGame(randomGameID, Date(), Date(), 0, true)
        tugDataRepo.createNewGame(newGame)
        txt_GameRoom_RoomID.text =
            String.format(getString(R.string.game_id_template), randomGameID)


        rvUsers = findViewById(R.id.rv_GameRoom_Users)
        rvUsers.layoutManager = LinearLayoutManager(this)

        NetworkHelper.mSocket.on("notification_user", onNewUser)
        NetworkHelper.mSocket.emit("newGame", randomGameID.toString())

        tugDataRepo.getUsersData().observe(this, {
            listUsers.clear()
            it.forEach { user ->
                listUsers.add(user.username)
            }

            adapter = UsersAdapter(listUsers)
            rvUsers.adapter = adapter
        })
    }

    private var onNewUser = Emitter.Listener {
        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getUsersInGame(randomGameID)
        }
    }

    fun onGameRoomChooseMcClick(view: View) {
        val chooseMCIntent = Intent(this, ChooseMainClaimActivity::class.java)
        chooseMCIntent.putExtra(GAME_ID_KEY, randomGameID)
        startActivity(chooseMCIntent)
    }
}