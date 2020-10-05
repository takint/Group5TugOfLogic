package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game_room.*
import specialtopic.groupfive.tugoflogic.R

class GameRoomActivity : AppCompatActivity() {
    lateinit var tempUsers: ArrayList<tempUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_room)

        txt_GameRoom_RoomID.setText("Room ID: 514")
        txt_GameRoom_Message.setText("Waiting for people to join your room")

        //Get students from database and set to the adapter later
        val rvUsers = findViewById<View>(R.id.rv_GameRoom_Users) as RecyclerView

        tempUsers = tempUser.createStudentsList(4)
        val adapter = StudentsAdapter(tempUsers)
        rvUsers.adapter = adapter
        rvUsers.layoutManager = LinearLayoutManager(this)

        btn_GameRoom_ChooseMC.setOnClickListener(View.OnClickListener {
            val chooseMCIntent = Intent(this, ChooseMainClaimActivity::class.java).apply {  }
            startActivity(chooseMCIntent)
        })
    }
}