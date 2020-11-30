package specialtopic.groupfive.tugoflogic.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_student_waiting_room.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.UsersAdapter
import specialtopic.groupfive.tugoflogic.student.StudentMainClaimActivity
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.USER_NAME_KEY

class StudentWaitingRoomActivity : AppCompatActivity() {
    var listUsers = ArrayList<String>()
    lateinit var roomID: String
    lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_waiting_room)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            roomID = bundle.getString(ROOM_ID_KEY).toString()
            username = bundle.getString(USER_NAME_KEY).toString()
            txt_StudentWaitingRoom_RoomID.text =
                String.format(getString(R.string.game_id_template), roomID.toString())
        }

        txt_StudentWaitingRoom_Message.text = getString(R.string.wait_for_users)

        val newUser = "$username:$roomID"
        NetworkHelper.mSocket.emit("newUser", newUser)

        NetworkHelper.mSocket.on("notification_user", onNewUser)
        NetworkHelper.mSocket.on("notification_startGame", onStartGame)
    }

    private var onNewUser = Emitter.Listener {
        var message = it[0] as String
        listUsers.clear()
        if (message.contains('[')) {
            message = message.substring(1, message.length - 1)
            message = message.replace("'", "", true)
            val userList = message.split(',')

            for (str in userList) {
                listUsers.add(str.trim())
            }
        } else {
            listUsers.add(message)
        }
        runOnUiThread(Runnable {
            updateView()
        })
    }

    private var onStartGame = Emitter.Listener {
        val intent = Intent(this, StudentMainClaimActivity::class.java)
        startActivity(intent)
    }

    private fun updateView() {
        if (listUsers.isNotEmpty()) {
            val rvUsers = findViewById<View>(R.id.rv_StudentWaitingRoom_Users) as RecyclerView
            val adapter = UsersAdapter(listUsers)
            rvUsers.adapter = adapter
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
    }
}