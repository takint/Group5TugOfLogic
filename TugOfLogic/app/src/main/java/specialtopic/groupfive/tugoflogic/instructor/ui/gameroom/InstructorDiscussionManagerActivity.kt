package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_choose_main_claim.*
import kotlinx.android.synthetic.main.activity_instructor__discussion_manager.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.MainClaimDissussionAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.student.StudentMainActivity
import specialtopic.groupfive.tugoflogic.utilities.GAME_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.MAIN_CLAIM
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.util.*
import kotlin.collections.ArrayList

class InstructorDiscussionManagerActivity : AppCompatActivity(), IMainClaim {
    var mainClaimList = ArrayList<MainClaim>()
    var isDisplay = false
    private lateinit var tugDataRepo: DataRepository
    var selectedGameId = 0
    lateinit var currentMC: MainClaim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor__discussion_manager)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            selectedGameId = bundle.getInt(GAME_ID_KEY, 0)
        }

        hideMainClaims()

        tugDataRepo = application?.let { DataRepository(it) }!!
        tugDataRepo.getMainClaimData().observe(this, Observer {
            val lstMainClaims = ArrayList<MainClaim>(it)
            for (mainClaim in lstMainClaims) {
                if (mainClaim.gameId.equals(selectedGameId)){
                    mainClaimList.add(mainClaim)
                }
            }
            updateView()
        })


        btnSummary.setOnClickListener(View.OnClickListener {
            val summaryIntent = Intent(this, GameSummaryActivity::class.java).apply {  }
            startActivity(summaryIntent)
        })

        btnSelectDiscussingMainClaim.setOnClickListener(View.OnClickListener {
            if(isDisplay){
                hideMainClaims()
            }
            else{
                displayMainClaims()
            }
        })

        btn_Instructor_StopDiscuss.setOnClickListener(View.OnClickListener {
            displayMainClaims()
            btnSelectDiscussingMainClaim.setText("Select MainClaim To Discuss")
        })

        btnGoToStudentMain.setOnClickListener(View.OnClickListener {
            if(this::currentMC.isInitialized){
                val intent = Intent(this, StudentMainActivity::class.java)
                intent.putExtra(MAIN_CLAIM, currentMC.mainClaimId)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "You have to pick a Main Claim first", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
        })

        NetworkHelper.mSocket.on("notification_current_mainclaim", onCurrentMainClaim)
    }
    fun displayMainClaims(){
        rsvDiscussingMainClaims.visibility = View.VISIBLE
        isDisplay = true
    }
    fun hideMainClaims(){
        rsvDiscussingMainClaims.visibility = View.GONE
        isDisplay = false
    }

    override fun updateMainClaimStatus(mainClaim: MainClaim, newStatus: Boolean) {

    }

    override fun setCurrentMainClaim(mainClaim: MainClaim) {
        btnSelectDiscussingMainClaim.setText(mainClaim.statement)
        if(!mainClaimList.isEmpty()){
            mainClaimList.remove(mainClaim)
            updateView()
            hideMainClaims()
            currentMC = mainClaim
        }
    }

    fun updateView(){
        runOnUiThread {
            val rcvDiscussingMainClaims = findViewById<View>(R.id.rsvDiscussingMainClaims) as RecyclerView
            val adapter = MainClaimDissussionAdapter(mainClaimList, this)
            rcvDiscussingMainClaims.adapter = adapter
            rcvDiscussingMainClaims.layoutManager = LinearLayoutManager(this)
        }
    }

    var onCurrentMainClaim = Emitter.Listener {
//        var message = it[0] as String
//        Log.i("Get Current MainClaim", message)
    }
}