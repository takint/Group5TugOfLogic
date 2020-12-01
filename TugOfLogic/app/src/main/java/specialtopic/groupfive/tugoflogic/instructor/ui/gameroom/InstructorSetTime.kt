package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_instructor_set_time.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY

class InstructorSetTime : AppCompatActivity() {

    var gameRoomId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_set_time)

        txt_SetTime_Message.text = getString(R.string.set_time_to_vote)

        val bundle = intent.extras
        if (bundle != null) {
            gameRoomId = bundle.getInt(ROOM_ID_KEY, 0)
            txt_SetTime_RoomID.text =
                String.format(getString(R.string.game_room_template), gameRoomId)
        }
    }

    fun onStartButtonClick(view: View) {
        if (edt_SetTime_Time.text.isNotEmpty()) {
            val time = edt_SetTime_Time.text.toString()
            NetworkHelper.mSocket.emit("setVotingTime", time)

            val startGameIntent = Intent(this, InstructorDiscussionManagerActivity::class.java)
            startActivity(startGameIntent)
        } else {
            edt_SetTime_Time.error = "Please input time to vote!"
        }
    }
}