package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_instructor_set_time.*
import specialtopic.groupfive.tugoflogic.R

class InstructorSetTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_set_time)

        txt_SetTime_Message.text = getString(R.string.set_time_to_vote)

        // Link to InstructorWaitingVoteActivity
        btn_Instructor_StartGame.setOnClickListener(View.OnClickListener {

            if (edt_SetTime_Time.text.isEmpty()) {
                edt_SetTime_Time.error = "Please input time to vote!"
                return@OnClickListener
            }
            val time = edt_SetTime_Time.text.toString()
            //save voteTime into sharedPreferences
            val sharedPref: SharedPreferences =
                getSharedPreferences(R.string.sharedPref.toString(), Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putInt(R.string.voteTime.toString(), time.toInt())
            editor.apply()

            val startGameIntent =
                Intent(this, InstructorDiscussionManagerActivity::class.java).apply { }
            startActivity(startGameIntent)
        })
    }
}