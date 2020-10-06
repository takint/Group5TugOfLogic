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

        val dummySummary2 = Instructor_Summary()
        dummySummary2.numStudents = 9
        dummySummary2.beginAgree = 7
        dummySummary2.beginDisagree = 2
        dummySummary2.endAgree = 4
        dummySummary2.endDisagree = 5

        val dummySummary3 = Instructor_Summary()
        dummySummary3.numStudents = 30
        dummySummary3.beginAgree = 10
        dummySummary3.beginDisagree = 20
        dummySummary3.endAgree = 15
        dummySummary3.endDisagree = 15

        val summaries = arrayListOf<Instructor_Summary>(dummySummary, dummySummary2, dummySummary3)

        val adapter = Instructor_SummaryAdapter(summaries)
        rvSummary.adapter = adapter
        rvSummary.layoutManager = LinearLayoutManager(this)


    }
}