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
    var listUsers = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_room)

        //Set RoomID and title later
        txt_GameRoom_Message.text = getString(R.string.wait_for_people)
        val randomGameID = Random.nextInt(100000, 1000000)

        // Init data repository for using on this fragment
        tugDataRepo = application?.let { DataRepository(it) }!!
        tugDataRepo.getGamesData().observe(this) {
            val newGame = TugGame(randomGameID, Date(), Date(), 0, true)
            tugDataRepo.createNewGame(this.application, newGame)
            txt_GameRoom_RoomID.text =
                String.format(getString(R.string.game_id_template), randomGameID)
        }

        btn_GameRoom_ChooseMC.setOnClickListener {
            val chooseMCIntent = Intent(this, ChooseMainClaimActivity::class.java).apply { }
            chooseMCIntent.putExtra(GAME_ID_KEY, randomGameID)
            startActivity(chooseMCIntent)
        }

        NetworkHelper.mSocket.on("notification_game_room", onNewGame)
        NetworkHelper.mSocket.on("notification_user", onNewUser)
        NetworkHelper.mSocket.emit("newGame", randomGameID.toString())

//        tugDataRepo.getUsersData().observe(this){
//            val instructor = User(0, "Instructor","Instructor","Instructor","Instructor","Instructor","Instructor","Instructor",randomGameID)
//            tugDataRepo.addNewUser(this.application, instructor)
//            listUsers.add("Instructor")
//            runOnUiThread {
//                updateView()
//            }
//        }

        runOnUiThread {
            updateView()
        }
    }


    var onNewGame = Emitter.Listener {

    }

    var onNewUser = Emitter.Listener {
        var message = it[0] as String
        if (message.contains('[')) {
            listUsers.clear()
            message = message.substring(1, message.length - 1)
            message = message.replace("'", "", true)
            val roomList = message.split(',')
            for (str in roomList) {
                listUsers.add(str.trim())
            }
        } else {
            listUsers.add(message)
        }
        runOnUiThread {
            updateView()
        }
    }

    private fun updateView() {
        if (listUsers.isNotEmpty()) {
            val rvUsers = findViewById<View>(R.id.rv_GameRoom_Users) as RecyclerView
            val adapter = UsersAdapter(listUsers)
            rvUsers.adapter = adapter
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
    }
}