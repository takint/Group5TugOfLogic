package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_instructor_set_time.*
import specialtopic.groupfive.tugoflogic.R

class Instructor_SetTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_set_time)

        txt_SetTime_Message.setText("Set Time To Vote")
    }
}