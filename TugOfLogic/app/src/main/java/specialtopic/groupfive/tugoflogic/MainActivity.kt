package specialtopic.groupfive.tugoflogic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.WorkerThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import specialtopic.groupfive.tugoflogic.instructor.InstructorMainActivity
import specialtopic.groupfive.tugoflogic.roomdb.ApiService
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.student.StudentChooseGameActivity
import specialtopic.groupfive.tugoflogic.student.StudentMainActivity
import specialtopic.groupfive.tugoflogic.student.StudentMainClaimActivity
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.lang.Exception
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init configuration from start
        NetworkHelper.getConfiguration(applicationContext);

        // Connect app to socket
        NetworkHelper.initSocket()
        NetworkHelper.openConnect()
    }

    fun onStudentBtnClick(view: View) {
        val intent = Intent(this, StudentChooseGameActivity::class.java)
        startActivity(intent)
    }

    fun onInstructorBtnClick(view: View) {
        val intent = Intent(this, InstructorMainActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
    }
}