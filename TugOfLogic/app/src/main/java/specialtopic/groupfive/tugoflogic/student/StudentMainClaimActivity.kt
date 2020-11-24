package specialtopic.groupfive.tugoflogic.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.student.adapters.StudentMainClaimListAdapter
import kotlin.collections.ArrayList

class StudentMainClaimActivity : AppCompatActivity() {

    private lateinit var tugDataRepo: DataRepository
    private lateinit var studentMainClaimListView: RecyclerView
    private lateinit var studentMainClaimAdapter: StudentMainClaimListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main_claim)

        tugDataRepo = DataRepository(application)
        tugDataRepo.getMainClaimData().observe(this, Observer {
            val studentMainClaims: ArrayList<MainClaim> = ArrayList<MainClaim>(it)
            studentMainClaimListView = findViewById(R.id.lstStudentMCToVote)
            studentMainClaimListView.setHasFixedSize(true)
            studentMainClaimListView.layoutManager = LinearLayoutManager(this)
            studentMainClaimAdapter = StudentMainClaimListAdapter(this, studentMainClaims)
            studentMainClaimListView.adapter = studentMainClaimAdapter
        })
    }

    fun onStartToPlayClick(view: View) {
        val intent = Intent(this, StudentMainActivity::class.java)
        startActivity(intent)
    }
}