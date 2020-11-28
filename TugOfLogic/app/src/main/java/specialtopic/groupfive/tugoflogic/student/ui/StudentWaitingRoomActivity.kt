package specialtopic.groupfive.tugoflogic.student.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_student_waiting_room.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.UsersAdapter
import specialtopic.groupfive.tugoflogic.student.StudentMainClaimActivity
import java.io.InputStream
import java.lang.Exception

class StudentWaitingRoomActivity : AppCompatActivity() {
    lateinit var roomID: String
    lateinit var sourseStr: String
    lateinit var mSocket: Socket;
    var listUsers = ArrayList<String>()
    lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_waiting_room)

        val bundle: Bundle? = intent.extras
        if(bundle!=null){
            roomID = bundle.getString("roomID").toString()
            username = bundle.getString("username").toString()
            txt_StudentWaitingRoom_RoomID.setText("Room ID: ${roomID}")
        }
        txt_StudentWaitingRoom_Message.setText("Waiting for other users")

        try {
            val inputStream: InputStream = assets.open("source.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            sourseStr = String(buffer)
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }

        try {
            mSocket = IO.socket(sourseStr)
            mSocket.connect()

        } catch (e: Exception) {

        }

        mSocket.emit("newUser", username)

        mSocket.on("notification_user", onNewUser)
        mSocket.on("notification_startGame", onStartGame)

    }

    var onNewUser = Emitter.Listener {
        val username = it[0] as String
        Log.i("User joined in: ", username)
        if(!username.equals("")){
            listUsers.add(username)
        }
        runOnUiThread(Runnable {
            updateView()
        })
    }

    var onStartGame = Emitter.Listener {
        val intent = Intent(this, StudentMainClaimActivity::class.java)
        mSocket.disconnect()
        startActivity(intent)
    }

    fun updateView(){
        if(!listUsers.isEmpty()){
            val rvUsers = findViewById<View>(R.id.rv_StudentWaitingRoom_Users) as RecyclerView
            val adapter = UsersAdapter(listUsers)
            rvUsers.adapter = adapter
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
    }
}