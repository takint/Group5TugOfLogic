package specialtopic.groupfive.tugoflogic.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_student_waiting_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.UsersAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.student.StudentMainClaimActivity
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.USER_NAME_KEY

class StudentWaitingRoomActivity : AppCompatActivity() {
    var listUsers = ArrayList<String>()
    var roomID: String = ""

    lateinit var tugDataRepo: DataRepository
    lateinit var username: String
    lateinit var rvUsers: RecyclerView
    lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_waiting_room)

        tugDataRepo = DataRepository(application)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            roomID = bundle.getString(ROOM_ID_KEY).toString()
            username = bundle.getString(USER_NAME_KEY).toString()
            txt_StudentWaitingRoom_RoomID.text =
                String.format(getString(R.string.game_id_template), roomID.toString())
        }

        txt_StudentWaitingRoom_Message.text = getString(R.string.wait_for_users)
        rvUsers = findViewById(R.id.rv_StudentWaitingRoom_Users)
        adapter = UsersAdapter(listUsers)
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        val newUser = "$username:$roomID"
        NetworkHelper.mSocket.emit("newUser", newUser)
        NetworkHelper.mSocket.on("notification_user", onNewUser)
        NetworkHelper.mSocket.on("notification_startGame", onStartGame)

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
            tugDataRepo.getUsersInGame(roomID.toInt())
        }
    }

    private var onStartGame = Emitter.Listener {
        val intent = Intent(this, StudentMainClaimActivity::class.java)
        intent.putExtra(ROOM_ID_KEY, roomID)
        intent.putExtra(USER_NAME_KEY, username)
        startActivity(intent)
    }
}