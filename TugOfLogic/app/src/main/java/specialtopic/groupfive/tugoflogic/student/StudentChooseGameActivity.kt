package specialtopic.groupfive.tugoflogic.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_student_choose_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.student.adapters.ChooseGameAdapter
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper

class StudentChooseGameActivity : AppCompatActivity() {
    private var listGameRoom = ArrayList<String>()
    private var userName = ""
    private lateinit var rvGameRooms: RecyclerView
    private lateinit var adapter: ChooseGameAdapter
    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_choose_game)

        tugDataRepo = DataRepository(application)
        NetworkHelper.mSocket.emit("getRunningGame")
        NetworkHelper.mSocket.on("notification_game_room", onNewGame)

        txt_ChooseGame_Title.text = getString(R.string.wait_game_room)
        rvGameRooms = findViewById(R.id.rsvStudentChooseGame)
        rvGameRooms.layoutManager = LinearLayoutManager(this)

        etStudentGuessName.addTextChangedListener {
            userName = etStudentGuessName.text.toString()
            adapter.setUserName(userName)
        }

        tugDataRepo.getGamesData().observe(this) {
            adapter = ChooseGameAdapter(listGameRoom)
            rvGameRooms.adapter = adapter
        }

        loadGameData()
    }

    private var onNewGame = Emitter.Listener {
        loadGameData()
    }

    private fun loadGameData() {
        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getGamesFromService()
        }
    }
}