package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game_room.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.student.Student

class GameRoomActivity : AppCompatActivity() {
    lateinit var students: ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_room)

        txt_GameRoom_RoomID.setText("Room ID: 514")

        //Get students from database and set to the adapter later
        val rvStudents = findViewById<View>(R.id.rv_GameRoom_Students) as RecyclerView

        students = Student.createStudentsList(4)
        val adapter = StudentsAdapter(students)
        rvStudents.adapter = adapter
        rvStudents.layoutManager = LinearLayoutManager(this)

        btn_GameRoom_ChooseMC.setOnClickListener(View.OnClickListener {
            val chooseMCIntent = Intent(this, ChooseMainClaimActivity::class.java).apply {  }
            startActivity(chooseMCIntent)
        })
    }
}