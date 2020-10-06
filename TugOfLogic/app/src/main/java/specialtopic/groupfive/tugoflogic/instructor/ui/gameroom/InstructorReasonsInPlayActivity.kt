package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorRipsAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay

class InstructorReasonsInPlayActivity : AppCompatActivity() {
    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_reasons_in_play)

        // Init data repository for using on this fragment
        tugDataRepo = application?.let { DataRepository(it) }!!

        tugDataRepo.getRipsData().observe(this, Observer {
            val listRips: ArrayList<ReasonInPlay> = ArrayList<ReasonInPlay>(it)

            //Get RIPs from database and set to the adapter later
            val rvInstructorRips = findViewById<View>(R.id.rv_instructor_rips) as RecyclerView

            // Currently using dummy data
            val adapter = InstructorRipsAdapter(listRips)
            rvInstructorRips.adapter = adapter
            rvInstructorRips.layoutManager = LinearLayoutManager(this)
        })
    }
}