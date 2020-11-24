package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_instructor_set_time.*
import kotlinx.android.synthetic.main.activity_instructor_waiting_vote.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import specialtopic.groupfive.tugoflogic.R

class InstructorWaitingVoteActivity : AppCompatActivity() {
    // TODO: should take the arg from prev activity - TEMP - Chau, Oct 5th 2020
    private var inputTime: Int = 2
    private var isRunning: Boolean = false
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_waiting_vote)

        // Link to InstructorReasonsInPlayActivity
        btn_instructor_start_discussion.setOnClickListener(View.OnClickListener {
            val startGameIntent =
                Intent(this, InstructorReasonsInPlayActivity::class.java).apply { }
            startActivity(startGameIntent)
        })

        // Countdown Timer
        initCountDownTimer()
    }

    private fun initCountDownTimer() {
        if (isRunning) {
            endTimer()
        } else {
            countdownTimer = object : CountDownTimer((inputTime.toLong() * 60000L), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    txt_waiting_clock_count_down.text =
                        ("Time: ${millisUntilFinished / 60000}:${(millisUntilFinished / 1000) % 60}").toString()
                }

                override fun onFinish() {
                    displayPieChart()
                    endTimer()
                }
            }
            startTimer()
        }
    }

    // TODO: should take the percent from student votes - TEMP - Chau, Oct 5th 2020
    private fun displayPieChart() {
        val percent: Float = 30.0f

        pieChart = findViewById<PieChart>(R.id.pie_chart_main_claim_vote)
        pieChart.addPieSlice(
            PieModel(
                "Agree", percent, Color.parseColor("#29B6F6")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Disagree", (100 - percent).toFloat(), Color.parseColor("#FFA726")
            )
        )
        pieChart.startAnimation()
    }

    private fun startTimer() {
        countdownTimer.start()
        isRunning = true
    }

    private fun endTimer() {
        countdownTimer.cancel()
        isRunning = false
        inputTime = 0
        txt_waiting_clock_count_down.text = "Time: 0:0"
    }
}