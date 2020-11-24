package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorSummaryAdapter

class GameSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_summary)

        //Get data later
        val rvSummary = findViewById<View>(R.id.rv_Summary) as RecyclerView

        val dummySummary = InstructorSummary()
        dummySummary.numStudents = 10
        dummySummary.beginAgree = 4
        dummySummary.beginDisagree = 6
        dummySummary.endAgree = 7
        dummySummary.endDisagree = 3

        val dummySummary2 = InstructorSummary()
        dummySummary2.numStudents = 9
        dummySummary2.beginAgree = 7
        dummySummary2.beginDisagree = 2
        dummySummary2.endAgree = 4
        dummySummary2.endDisagree = 5

        val dummySummary3 = InstructorSummary()
        dummySummary3.numStudents = 30
        dummySummary3.beginAgree = 10
        dummySummary3.beginDisagree = 20
        dummySummary3.endAgree = 15
        dummySummary3.endDisagree = 15

        val summaries = arrayListOf<InstructorSummary>(dummySummary, dummySummary2, dummySummary3)

        val adapter = InstructorSummaryAdapter(summaries)
        rvSummary.adapter = adapter
        rvSummary.layoutManager = LinearLayoutManager(this)


    }
}