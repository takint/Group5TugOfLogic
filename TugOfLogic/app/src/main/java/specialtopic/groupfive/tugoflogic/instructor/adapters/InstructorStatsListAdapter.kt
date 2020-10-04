package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import specialtopic.groupfive.tugoflogic.R

class InstructorStatsListAdapter(
    private val context: Activity,
    private val gameIds: Array<String>,
    private val dateTimestamps: Array<String>
) : ArrayAdapter<String>(context, R.layout.layout_instructor_mainclaim_item, gameIds) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        // Check if an existing view is being reused, otherwise inflate the view
        val inflater = context.layoutInflater
        val itemView = inflater.inflate(R.layout.layout_instructor_statistics_item, parent, false)

        val txtGameId = itemView.findViewById(R.id.textView_statistics_game_id) as TextView
        val txtDateTimestamps =
            itemView.findViewById(R.id.textView_statistics_game_date_time) as TextView

        txtGameId.text = gameIds[position]
        txtDateTimestamps.text = dateTimestamps[position]

        return itemView
    }
}