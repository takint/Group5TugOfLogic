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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.student.StudentMainActivity
import specialtopic.groupfive.tugoflogic.utilities.GAME_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.USER_NAME_KEY
import java.io.InputStream

class ChooseMainClaimActivity : AppCompatActivity(), IMainClaim {
    private lateinit var tugDataRepo: DataRepository
    private lateinit var rvMCs: RecyclerView
    private lateinit var adapter: InstructorChooseMCAdapter
    private var mainClaims = HashMap<MainClaim, Boolean>()
    var selectedGameId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_main_claim)

        tugDataRepo = DataRepository(application)

        //Set RoomID and title later
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            selectedGameId = bundle.getInt(GAME_ID_KEY, 0)
            txt_ChooseMC_RoomID.text =
                String.format(getString(R.string.game_room_template), selectedGameId.toString())
        }

        txt_ChooseMC_Message.text = getString(R.string.choose_mc)
        rvMCs = findViewById(R.id.rv_ChooseMC_MCs)
        rvMCs.layoutManager = LinearLayoutManager(this)

        tugDataRepo.getMainClaimData().observe(this, Observer {
            val lstMainClaims = ArrayList<MainClaim>(it)
            for (mainClaim in lstMainClaims) {
                mainClaims[mainClaim] = false
            }
            adapter = InstructorChooseMCAdapter(mainClaims, this)
            rvMCs.adapter = adapter
        })

        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getMCsFromService(application)
        }
    }

    fun onChooseMCButtonClick(view: View) {
        if (mainClaims.containsValue(true)) {
            val gameMCSelected = StringBuilder("$selectedGameId:")
            mainClaims.entries.forEach {
                if (it.value) {
                    gameMCSelected.append("${it.key.mainClaimId},")
                }
            }
            NetworkHelper.mSocket.emit("startGame", gameMCSelected.toString())

            val setTimeIntent = Intent(this, InstructorSetTime::class.java)
            setTimeIntent.putExtra(ROOM_ID_KEY, selectedGameId)
            startActivity(setTimeIntent)
        } else {
            Toast.makeText(
                this,
                "Select at least one Main Claim to continue",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun updateMainClaimStatus(mainClaim: MainClaim, newStatus: Boolean) {
        mainClaims[mainClaim] = newStatus
        adapter = InstructorChooseMCAdapter(mainClaims, this)
        rvMCs.adapter = adapter
    }
}