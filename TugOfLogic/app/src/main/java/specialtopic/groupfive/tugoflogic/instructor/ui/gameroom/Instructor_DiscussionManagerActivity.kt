package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_instructor__discussion_manager.*
import specialtopic.groupfive.tugoflogic.R

class Instructor_DiscussionManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor__discussion_manager)

        btnSummary.setOnClickListener(View.OnClickListener {
            val summaryIntent = Intent(this, GameSummaryActivity::class.java).apply {  }
            startActivity(summaryIntent)
        })
    }
}