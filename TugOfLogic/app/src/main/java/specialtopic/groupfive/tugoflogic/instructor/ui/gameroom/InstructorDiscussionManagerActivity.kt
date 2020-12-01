package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_choose_main_claim.*
import kotlinx.android.synthetic.main.activity_instructor__discussion_manager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor__discussion_manager)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            selectedGameId = bundle.getInt(GAME_ID_KEY, 0)
            Log.i("CURRENT GAME ID: ", selectedGameId.toString())
        }

        btnSummary.visibility = View.GONE

        hideMainClaims()
        txtDiscussing.visibility = View.GONE

        tugDataRepo = application?.let { DataRepository(it) }!!
        tugDataRepo.getMainClaimData().observe(this, Observer {
            mainClaimList = ArrayList(it)
            updateView()
        })
        CoroutineScope(Dispatchers.IO).launch {

            tugDataRepo.getMainClaimOnGame(selectedGameId)
        }

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
            btnSelectDiscussingMainClaim.text = getString(R.string.select_main_claim)
            Toast.makeText(this, "Select new Main Claim to discuss", Toast.LENGTH_SHORT).show()
            txtDiscussing.visibility = View.GONE
            isRunning = false
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

        btnEndGame.setOnClickListener(View.OnClickListener {

            if(!mainClaimList.isEmpty()){
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("End Game Alert")
                alertDialogBuilder.setMessage("There are still main claims that haven't discuss, Do you want to end game?")
                alertDialogBuilder.setPositiveButton(android.R.string.yes){dialog, which ->
                    endGame()
                }
                alertDialogBuilder.setNegativeButton(android.R.string.no){dialog, which ->
                    return@setNegativeButton
                }
                alertDialogBuilder.show()
            }else{
                endGame()
            }
        })

        NetworkHelper.mSocket.on("notification_current_mainclaim", onCurrentMainClaim)
    }
    private fun displayMainClaims(){
        rsvDiscussingMainClaims.visibility = View.VISIBLE
        isDisplay = true
    }
    private fun hideMainClaims(){
        rsvDiscussingMainClaims.visibility = View.GONE
        isDisplay = false
    }

    override fun updateMainClaimStatus(mainClaim: MainClaim, newStatus: Boolean) {

    }

    override fun setCurrentMainClaim(mainClaim: MainClaim) {

        if(mainClaimList.isNotEmpty()){
            btnSelectDiscussingMainClaim.text = mainClaim.statement
            updateView()
            hideMainClaims()
            mainClaimList.remove(mainClaim)
            currentMC = mainClaim
            txtDiscussing.visibility = View.VISIBLE
            isRunning = true
        }
    }

    fun updateView(){
        runOnUiThread {
            val rcvDiscussingMainClaims = findViewById<View>(R.id.rsvDiscussingMainClaims) as RecyclerView
            val adapter = MainClaimDissussionAdapter(mainClaimList, this)
            rcvDiscussingMainClaims.adapter = adapter
            rcvDiscussingMainClaims.layoutManager = LinearLayoutManager(this)

            CoroutineScope(Dispatchers.Main).launch {
                while(isRunning){
                    txtDiscussing.text = "Students Discussing."
                    delay(1000)
                    txtDiscussing.text = "Students Discussing.."
                    delay(1000)
                    txtDiscussing.text = "Students Discussing..."
                    delay(1000)
                }
            }
        }
    }

    var onCurrentMainClaim = Emitter.Listener {
//        var message = it[0] as String
//        Log.i("Get Current MainClaim", message)
    }

    fun endGame(){
        NetworkHelper.mSocket.emit("endGame", selectedGameId)
        val summaryIntent = Intent(this, GameSummaryActivity::class.java)
        summaryIntent.putExtra(GAME_ID_KEY, selectedGameId)
        startActivity(summaryIntent)
    }
}