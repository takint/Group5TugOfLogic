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
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import java.io.InputStream

class ChooseMainClaimActivity : AppCompatActivity(), IMainClaim {
    private lateinit var tugDataRepo: DataRepository
    var mainclaims = HashMap<MainClaim, Boolean>()
    lateinit var sourseStr: String
    lateinit var mSocket: Socket;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_main_claim)

        //Set RoomID and title later
        //txt_ChooseMC_RoomID.setText("Room ID: 514")
        txt_ChooseMC_Message.setText("Choosing Main Claim")

        tugDataRepo = application?.let { DataRepository(it) }!!

        tugDataRepo.getMainClaimData().observe(this, Observer {
            val lstMainClaims: ArrayList<MainClaim> = ArrayList<MainClaim>(it)

            for (mainclaim in lstMainClaims){
                mainclaims.put(mainclaim, false)
            }

            //Get MainClaims from database and set to the adapter later
            val rvMCs = findViewById<View>(R.id.rv_ChooseMC_MCs) as RecyclerView

            val adapter = InstructorChooseMCAdapter(mainclaims, this)
            rvMCs.adapter = adapter
            rvMCs.layoutManager = LinearLayoutManager(this)

        })
        // Init data repository for using on this fragment

        try {
            val inputStream: InputStream = assets.open("source.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            sourseStr = String(buffer)
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }

        try {
            mSocket = IO.socket(sourseStr)
            mSocket.connect()

        } catch (e: Exception) {

        }

        btn_ChooseMC_Next.setOnClickListener(View.OnClickListener {
            if(!mainclaims.containsValue(true)){
                Toast.makeText(this, "Select at least one Main Claim to continue", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val setTimeIntent = Intent(this, InstructorSetTime::class.java)
            mSocket.emit("startGame")
            mSocket.disconnect()
            startActivity(setTimeIntent)
        })
    }

    override fun updateMainClaimStatus(mainClaim: MainClaim, newStatus: Boolean) {
        mainclaims[mainClaim] = newStatus
        updateView()
    }

    fun updateView(){
        val rvMCs = findViewById<View>(R.id.rv_ChooseMC_MCs) as RecyclerView
        val adapter = InstructorChooseMCAdapter(mainclaims, this)
        rvMCs.adapter = adapter
        rvMCs.layoutManager = LinearLayoutManager(this)
    }

}