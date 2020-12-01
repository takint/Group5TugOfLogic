package specialtopic.groupfive.tugoflogic.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_student_waiting_room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.student.adapters.StudentMainClaimListAdapter
import specialtopic.groupfive.tugoflogic.utilities.MAIN_CLAIM
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.USER_NAME_KEY
import kotlin.collections.ArrayList

class StudentMainClaimActivity : AppCompatActivity() {

    private lateinit var tugDataRepo: DataRepository
    private lateinit var studentMainClaimListView: RecyclerView
    private lateinit var studentMainClaimAdapter: StudentMainClaimListAdapter
    lateinit var roomID: String
    lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main_claim)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            roomID = bundle.getString(ROOM_ID_KEY).toString()
            username = bundle.getString(USER_NAME_KEY).toString()
        }

        tugDataRepo = DataRepository(application)

        studentMainClaimListView = findViewById(R.id.lstStudentMCToVote)
        studentMainClaimListView.setHasFixedSize(true)
        studentMainClaimListView.layoutManager = LinearLayoutManager(this)


        tugDataRepo.getMainClaimData().observe(this, Observer {
            val studentMainClaims: ArrayList<MainClaim> = ArrayList(it)
            studentMainClaimAdapter = StudentMainClaimListAdapter(this, studentMainClaims)
            studentMainClaimListView.adapter = studentMainClaimAdapter
        })
        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getMainClaimOnGame(roomID.toInt())
        }

        NetworkHelper.mSocket.on("notification_current_mainclaim", onCurrentMainClaim)
    }

    fun onStartToPlayClick(view: View) {
        val intent = Intent(this, StudentMainActivity::class.java)

        intent.putExtra(ROOM_ID_KEY, roomID)
        intent.putExtra(USER_NAME_KEY, username)

        startActivity(intent)
    }

    var onCurrentMainClaim = Emitter.Listener {
        var message = it[0] as String
        val intent = Intent(this, StudentMainActivity::class.java)
        intent.putExtra(ROOM_ID_KEY, roomID)
        intent.putExtra(USER_NAME_KEY, username)
        intent.putExtra(MAIN_CLAIM, message.toInt())
        startActivity(intent)
    }
}