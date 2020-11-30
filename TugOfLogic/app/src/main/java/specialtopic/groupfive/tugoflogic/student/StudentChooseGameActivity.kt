package specialtopic.groupfive.tugoflogic.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_student_choose_game.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.student.adapters.ChooseGameAdapter
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper

class StudentChooseGameActivity : AppCompatActivity() {
    var listGameRoom = ArrayList<String>()
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_choose_game)

        NetworkHelper.mSocket.emit("getRunningGame")
        NetworkHelper.mSocket.on("notification_game_room", onNewGame)

        etStudentGuessName.addTextChangedListener {
            userName = etStudentGuessName.text.toString()
            runOnUiThread {
                updateView()
            }
        }
    }

    private var onNewGame = Emitter.Listener {
        var message = it[0] as String
        if (message.contains('[')) {
            message = message.substring(1, message.length - 1)
            message = message.replace("'", "", true)
            val roomList = message.split(',')
            for (str in roomList) {
                listGameRoom.add(str.trim())
            }
        } else {
            listGameRoom.add(message)
        }
        runOnUiThread {
            updateView()
        }
    }

    private fun updateView() {
        if (listGameRoom.size > 0) {
            txt_ChooseGame_Title.text = getString(R.string.game_room)
            val rvGameRooms = findViewById<View>(R.id.rsvStudentChooseGame) as RecyclerView
            if (userName.isBlank()) {
                userName = getString(R.string.no_name)
            }
            val adapter = ChooseGameAdapter(listGameRoom, userName)
            rvGameRooms.adapter = adapter
            rvGameRooms.layoutManager = LinearLayoutManager(this)
        } else {
            txt_ChooseGame_Title.text = getString(R.string.wait_game_room)
        }
    }
}