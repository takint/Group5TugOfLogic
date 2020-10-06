package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_choose_main_claim.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter

class ChooseMainClaimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_main_claim)

        //Set RoomID and title later
        txt_ChooseMC_RoomID.setText("Room ID: 514")
        txt_ChooseMC_Message.setText("Choosing Main Claim")

        //Get MainClaims from database and set to the adapter later
        val rvMCs = findViewById<View>(R.id.rv_ChooseMC_MCs) as RecyclerView

        //Currently using dummy data
        val MainClaims: ArrayList<String> = arrayListOf("Main Claim 1", "Main Claim 2", "Main Claim 3", "Main Claim 4")
        val adapter = InstructorChooseMCAdapter(MainClaims)
        rvMCs.adapter = adapter
        rvMCs.layoutManager = LinearLayoutManager(this)

        btn_ChooseMC_Next.setOnClickListener(View.OnClickListener {
            val setTimeIntent = Intent(this, Instructor_SetTime::class.java).apply {   }
            startActivity(setTimeIntent)
        })
    }
}