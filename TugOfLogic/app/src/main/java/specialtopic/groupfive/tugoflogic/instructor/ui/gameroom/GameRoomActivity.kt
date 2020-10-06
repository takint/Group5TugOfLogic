package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game_room.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.UsersAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.User

class GameRoomActivity : AppCompatActivity() {
    lateinit var tempUsers: ArrayList<tempUser>
    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_room)

        //Set RoomID and title later
        //txt_GameRoom_RoomID.setText("Room ID: 514")
        txt_GameRoom_Message.setText("Waiting for people to join your room")

        // Init data repository for using on this fragment
        tugDataRepo = application?.let { DataRepository(it) }!!

        tugDataRepo.getUsersData().observe(this, Observer {
            val lstUser: ArrayList<User> = ArrayList<User>(it)

            val rvUsers = findViewById<View>(R.id.rv_GameRoom_Users) as RecyclerView
            val adapter = UsersAdapter(lstUser)
            rvUsers.adapter = adapter
            rvUsers.layoutManager = LinearLayoutManager(this)
        })


        btn_GameRoom_ChooseMC.setOnClickListener(View.OnClickListener {
            val chooseMCIntent = Intent(this, ChooseMainClaimActivity::class.java).apply {  }
            startActivity(chooseMCIntent)
        })
    }
}