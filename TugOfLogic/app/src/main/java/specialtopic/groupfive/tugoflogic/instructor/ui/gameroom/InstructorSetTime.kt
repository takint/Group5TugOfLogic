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

        txt_SetTime_Message.text = "Set Time To Vote"

        // Link to InstructorWaitingVoteActivity
        btn_Instructor_StartGame.setOnClickListener(View.OnClickListener {
<<<<<<< HEAD
            if(edt_SetTime_Time.text.isEmpty()){
                edt_SetTime_Time.setError("Please input time to vote!")
                return@OnClickListener
            }
            val time = edt_SetTime_Time.text.toString()
            //save voteTime into sharedPreferences
            val sharedPref: SharedPreferences = getSharedPreferences(R.string.sharedPref.toString(), Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putInt(R.string.voteTime.toString(), time.toInt())
            editor.apply()

            val startGameIntent = Intent(this, InstructorDiscussionManagerActivity::class.java).apply {  }
=======
            val startGameIntent =
                Intent(this, InstructorWaitingVoteActivity::class.java).apply { }
>>>>>>> 139f5448180f502d8026e302bdc585f00df8b894
            startActivity(startGameIntent)
        })
    }
}