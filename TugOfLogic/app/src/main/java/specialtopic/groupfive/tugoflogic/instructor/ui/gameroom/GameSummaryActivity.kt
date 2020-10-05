package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_choose_main_claim.*
import specialtopic.groupfive.tugoflogic.R

class GameSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_summary)

        //Get students from database and set to the adapter later
        val rvSummary = findViewById<View>(R.id.rv_Summary) as RecyclerView

        val dummySummary = Instructor_Summary()
        dummySummary.numStudents = 10
        dummySummary.beginAgree = 4
        dummySummary.beginDisagree = 6
        dummySummary.endAgree = 7
        dummySummary.endDisagree = 3

        val summaries = arrayListOf<Instructor_Summary>(dummySummary)

        val adapter = Instructor_SummaryAdapter(summaries)
        rvSummary.adapter = adapter
        rvSummary.layoutManager = LinearLayoutManager(this)
    }
}