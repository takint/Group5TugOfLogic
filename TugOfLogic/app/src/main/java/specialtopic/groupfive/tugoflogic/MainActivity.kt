package specialtopic.groupfive.tugoflogic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import specialtopic.groupfive.tugoflogic.instructor.InstructorMainActivity
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.student.StudentMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStudentBtnClick(view: View) {
        val intent = Intent(this, StudentMainActivity::class.java)
        startActivity(intent)
    }

    fun onInstructorBtnClick(view: View) {
        val intent = Intent(this, InstructorMainActivity::class.java)
        startActivity(intent)
    }
}