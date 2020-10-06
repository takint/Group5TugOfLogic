package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_choose_main_claim.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

class ChooseMainClaimActivity : AppCompatActivity() {
    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_main_claim)

        //Set RoomID and title later
        txt_ChooseMC_RoomID.setText("Room ID: 514")
        txt_ChooseMC_Message.setText("Choosing Main Claim")

        // Init data repository for using on this fragment
        tugDataRepo = application?.let { DataRepository(it) }!!

        tugDataRepo.getMainClaimData().observe(this, Observer {
            val lstMainClaims: ArrayList<MainClaim> = ArrayList<MainClaim>(it)

            //Get MainClaims from database and set to the adapter later
            val rvMCs = findViewById<View>(R.id.rv_ChooseMC_MCs) as RecyclerView

            //Currently using dummy data
            val adapter = InstructorChooseMCAdapter(lstMainClaims)
            rvMCs.adapter = adapter
            rvMCs.layoutManager = LinearLayoutManager(this)

        })

        btn_ChooseMC_Next.setOnClickListener(View.OnClickListener {
            val setTimeIntent = Intent(this, InstructorSetTime::class.java).apply {   }
            startActivity(setTimeIntent)
        })
    }
}