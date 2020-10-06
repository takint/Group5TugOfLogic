package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_instructor_set_time.*
import specialtopic.groupfive.tugoflogic.R

class InstructorSetTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_set_time)

        txt_SetTime_Message.text = "Set Time To Vote"

        // Link to InstructorWaitingVoteActivity
        btn_Instructor_StartGame.setOnClickListener(View.OnClickListener {
            val startGameIntent =
                Intent(this, InstructorWaitingVoteActivity::class.java).apply { }
            startActivity(startGameIntent)
        })
    }
}