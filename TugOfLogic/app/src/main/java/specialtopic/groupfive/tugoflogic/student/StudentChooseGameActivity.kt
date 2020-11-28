package specialtopic.groupfive.tugoflogic.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_student_choose_game.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.student.adapters.ChooseGameAdapter
import java.io.InputStream

class StudentChooseGameActivity : AppCompatActivity() {
    lateinit var mSocket: Socket
    lateinit var roomName: String
    lateinit var sourseStr: String
    var listGameRoom = ArrayList<String>()
    lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_choose_game)
        username = ""



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
        mSocket.emit("getRunningGame", username)

        mSocket.on("notification_game_room", onNewGame)

        runOnUiThread(Runnable {
            updateView()
        })

        edt_StudentChooseGame_Username.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                username = edt_StudentChooseGame_Username.text.toString()
                runOnUiThread(Runnable {
                    updateView()
                })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    var onNewGame = Emitter.Listener {
        var message = it[0] as String
        if(message.contains('[')){
            message = message.substring(1, message.length-1)
            message = message.replace("'", "", true)
            var roomList = message.split(',')
            for(str in roomList){
                listGameRoom.add(str.trim())
            }
        }
        else{
            listGameRoom.add(message)
        }
        runOnUiThread(Runnable {
            updateView()
        })
    }

    fun updateView(){
        if(listGameRoom.size > 0){
            Log.i("TEST USERNAME: ", username)
            txt_ChooseGame_Title.setText("Game Room")
            val rvGameRooms = findViewById<View>(R.id.rsvStudentChooseGame) as RecyclerView
            if(username.isNullOrBlank()){
                username = "No Name User"
            }
            val adapter = ChooseGameAdapter(listGameRoom, username)
            rvGameRooms.adapter = adapter
            rvGameRooms.layoutManager = LinearLayoutManager(this)
        }
        else{
            txt_ChooseGame_Title.setText("Waiting Game Room")
        }
    }
}