package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.Instructor_Summary

/**
 * Adapter for summary
 */
class Instructor_SummaryAdapter(private val summaries: ArrayList<Instructor_Summary>): RecyclerView.Adapter<Instructor_SummaryAdapter.ViewHolder>() {

    inner class  ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val numStudents = itemView.findViewById<TextView>(R.id.txt_Summary_numStudents)
        val numBeginAgree = itemView.findViewById<TextView>(R.id.txt_Summary_BeginAgree)
        val numBeginDisagree = itemView.findViewById<TextView>(R.id.txt_Summary_BeginDisagree)
        val numEndAgree = itemView.findViewById<TextView>(R.id.txt_Summary_EndAgree)
        val numEndDisagree = itemView.findViewById<TextView>(R.id.txt_Summary_EndDisagree)

        val pieChart = itemView.findViewById<PieChart>(R.id.Instructor_Summary_piechart)
        val txtSummaryAgree = itemView.findViewById<TextView>(R.id.txtSummaryAgree)
        val txtSummaryDisagree = itemView.findViewById<TextView>(R.id.txtSummaryDisagree)

        val txtSummaryTitle = itemView.findViewById<TextView>(R.id.txtSummaryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val summaryView = inflater.inflate(R.layout.instructor_summary_item, parent, false)

        return ViewHolder(summaryView)
    }

    override fun getItemCount(): Int {
        return summaries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val summary: Instructor_Summary = summaries.get(position)

        val txtNumStudents = holder.numStudents
        val txtnumBeginAgree = holder.numBeginAgree
        val txtnumBeginDisagree = holder.numBeginDisagree
        val txtnumEndAgree = holder.numEndAgree
        val txtnumEndDisagree = holder.numEndDisagree

        val txtSummaryAgree = holder.txtSummaryAgree
        val txtSummaryDisagree = holder.txtSummaryDisagree
        val txtSummaryTitle = holder.txtSummaryTitle

        txtSummaryTitle.setText("Main Claim ${position+1}")

        txtNumStudents.setText("Number of Students: ${summary.numStudents}")
        txtnumBeginAgree.setText("Begin With Agree: ${summary.beginAgree}")
        txtnumBeginDisagree.setText("Begin With Disagree: ${summary.beginDisagree}")
        txtnumEndAgree.setText("End With Agree: ${summary.endAgree}")
        txtnumEndDisagree.setText("End With Disagree: ${summary.endDisagree}")

        val percent: Float = (summary.endAgree.toFloat() / summary.numStudents.toFloat())*100

        val pieChart = holder.pieChart
        pieChart.addPieSlice(
            PieModel(
            "Agree", percent, Color.parseColor("#29B6F6")
        ))
        pieChart.addPieSlice(
            PieModel(
            "Disagree", (100-percent).toFloat(), Color.parseColor("#FFA726")
        ))

        txtSummaryAgree.setText("Agree " + percent.toInt() + "%")
        txtSummaryDisagree.setText("Disagree " + (100-percent).toInt() + "%")

        pieChart.startAnimation()
    }


}