package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_choose_main_claim.*
import kotlinx.android.synthetic.main.activity_student_waiting_room.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.utilities.GAME_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.USER_NAME_KEY
import java.io.InputStream

class ChooseMainClaimActivity : AppCompatActivity(), IMainClaim {
    private lateinit var tugDataRepo: DataRepository

    private var mainClaims = HashMap<MainClaim, Boolean>()
    var selectedGameId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_main_claim)

        //Set RoomID and title later
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            selectedGameId = bundle.getInt(GAME_ID_KEY, 0)
            txt_ChooseMC_RoomID.text =
                String.format(getString(R.string.game_room_template), selectedGameId.toString())
        }

        txt_ChooseMC_Message.text = getString(R.string.choose_mc)
        tugDataRepo = application?.let { DataRepository(it) }!!
        tugDataRepo.getMainClaimData().observe(this, Observer {
            val lstMainClaims = ArrayList<MainClaim>(it)
            for (mainClaim in lstMainClaims) {
                mainClaims[mainClaim] = false
            }

            //Get MainClaims from database and set to the adapter later
            val rvMCs = findViewById<View>(R.id.rv_ChooseMC_MCs) as RecyclerView

            val adapter = InstructorChooseMCAdapter(mainClaims, this)
            rvMCs.adapter = adapter
            rvMCs.layoutManager = LinearLayoutManager(this)
        })

        // Init data repository for using on this fragment
        btn_ChooseMC_Next.setOnClickListener(View.OnClickListener {
            if (!mainClaims.containsValue(true)) {
                Toast.makeText(
                    this,
                    "Select at least one Main Claim to continue",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            val setTimeIntent = Intent(this, InstructorSetTime::class.java)
            val gameMCSelected = StringBuilder("$selectedGameId:")
            mainClaims.entries.forEach {
                if (it.value) {
                    gameMCSelected.append("${it.key.mainClaimId},")
                }
            }

            Log.i("GAME MC SELECTED: ", gameMCSelected.toString())

            NetworkHelper.mSocket.emit("startGame", gameMCSelected.toString())
            startActivity(setTimeIntent)
        })
    }

    override fun updateMainClaimStatus(mainClaim: MainClaim, newStatus: Boolean) {
        mainClaims[mainClaim] = newStatus
        updateView()
    }

    private fun updateView() {
        val rvMCs = findViewById<View>(R.id.rv_ChooseMC_MCs) as RecyclerView
        val adapter = InstructorChooseMCAdapter(mainClaims, this)
        rvMCs.adapter = adapter
        rvMCs.layoutManager = LinearLayoutManager(this)
    }

}