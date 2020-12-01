package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorSummaryAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.student.adapters.ChooseGameAdapter
import specialtopic.groupfive.tugoflogic.utilities.GAME_ID_KEY
import kotlin.random.Random

class GameSummaryActivity : AppCompatActivity() {
    var selectedGameId = 0
    private lateinit var rvSummary: RecyclerView
    private lateinit var adapter: InstructorSummaryAdapter
    private lateinit var tugDataRepo: DataRepository
    var mainClaimList = ArrayList<MainClaim>()
    var summaries = ArrayList<InstructorSummary>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_summary)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            selectedGameId = bundle.getInt(GAME_ID_KEY, 0)
        }

        rvSummary = findViewById<View>(R.id.rv_Summary) as RecyclerView

        tugDataRepo = application?.let { DataRepository(it) }!!
        tugDataRepo.getMainClaimData().observe(this, Observer {
            mainClaimList = ArrayList(it)

            for (mainClaim: MainClaim in mainClaimList){
                val summary = InstructorSummary()
                summary.title = mainClaim.statement
                summary.numStudents = Random.nextInt(6,9)
                summary.beginAgree = Random.nextInt(0,9)
                summary.beginDisagree = summary.numStudents - summary.beginAgree
                summary.endAgree = Random.nextInt(0,9)
                summary.endDisagree = summary.numStudents - summary.endAgree

                summaries.add(summary)
                adapter = InstructorSummaryAdapter(summaries)
                rvSummary.adapter = adapter
                rvSummary.layoutManager = LinearLayoutManager(this)
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getMainClaimOnGame(selectedGameId)
        }


    }
}