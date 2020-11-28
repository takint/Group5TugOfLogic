package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_game_room.*
import kotlinx.android.synthetic.main.activity_student_choose_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.WEB_SERVICE_URL
import specialtopic.groupfive.tugoflogic.instructor.adapters.UsersAdapter
import specialtopic.groupfive.tugoflogic.roomdb.ApiService
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.User
import specialtopic.groupfive.tugoflogic.student.adapters.ChooseGameAdapter
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.io.InputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class GameRoomActivity : AppCompatActivity() {
    private lateinit var tugDataRepo: DataRepository
    lateinit var sourseStr: String
    lateinit var mSocket: Socket;
    var listUsers = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_room)

        //Set RoomID and title later

        txt_GameRoom_Message.setText("Waiting for people to join your room")

        val randomGameID = Random.nextInt(100000, 1000000)

        // Init data repository for using on this fragment
        tugDataRepo = application?.let { DataRepository(it) }!!

//        tugDataRepo.getUsersData().observe(this, Observer {
//            val lstUser: ArrayList<User> = ArrayList<User>(it)
//
//            for(user in lstUser){
//                Log.i("Users: ", user.username)
//            }
//
//            val rvUsers = findViewById<View>(R.id.rv_GameRoom_Users) as RecyclerView
//            val adapter = UsersAdapter(lstUser)
//            rvUsers.adapter = adapter
//            rvUsers.layoutManager = LinearLayoutManager(this)
//        })

        tugDataRepo.getGamesData().observe(this, Observer {
            var newGame: TugGame = TugGame(randomGameID, Date(), Date(), 0, true)
            tugDataRepo.createNewGame(this.application, newGame)

            txt_GameRoom_RoomID.setText("Game ID: ${randomGameID}")
        })

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

        mSocket.on(Socket.EVENT_CONNECT, Emitter.Listener {

        });

        btn_GameRoom_ChooseMC.setOnClickListener(View.OnClickListener {
            val chooseMCIntent = Intent(this, ChooseMainClaimActivity::class.java).apply {  }
            startActivity(chooseMCIntent)
        })

        mSocket.on("notification_game_room", onNewGame)
        mSocket.on("notification_user", onNewUser)

        mSocket.emit("newGame", randomGameID.toString())

        runOnUiThread(Runnable {
            updateView()
        })
    }

    var onNewGame = Emitter.Listener {

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

    fun updateView(){
        if(!listUsers.isEmpty()){
            val rvUsers = findViewById<View>(R.id.rv_GameRoom_Users) as RecyclerView
            val adapter = UsersAdapter(listUsers)
            rvUsers.adapter = adapter
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
    }
}