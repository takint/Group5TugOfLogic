package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_instructor__discussion_manager.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.MainClaimDissussionAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import java.util.*
import kotlin.collections.ArrayList

class InstructorDiscussionManagerActivity : AppCompatActivity() {
    var mainClaimList = ArrayList<String>()
    var isDisplay = false
    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor__discussion_manager)

        tugDataRepo = application?.let { DataRepository(it) }!!
        tugDataRepo.getMainClaimData().observe(this, Observer {
            val lstMainClaims = tugDataRepo.getMainClaimByGameId()
        })

        val rcvDiscussingMainClaims = findViewById<View>(R.id.rsvDiscussingMainClaims) as RecyclerView

        val adapter = MainClaimDissussionAdapter(mainClaimList)
        rcvDiscussingMainClaims.adapter = adapter
        rcvDiscussingMainClaims.layoutManager = LinearLayoutManager(this)


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
    }
    fun displayMainClaims(){
        rsvDiscussingMainClaims.visibility = View.VISIBLE
        isDisplay = true
    }
    fun hideMainClaims(){
        rsvDiscussingMainClaims.visibility = View.GONE
        isDisplay = false
    }
}