package specialtopic.groupfive.tugoflogic.instructor.ui.instructorstatistics

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorMainClaimsListAdapter
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorStatsListAdapter

class InstructorStatisticsFragment : Fragment() {

    private lateinit var instructorStatisticsViewModel: InstructorStatisticsViewModel

    // Dummy Data - Delete when implement database
    private val gameIds = arrayOf<String>(
        "ABC123",
        "ABC456",
        "XYZ789",
    )

    private val gameDateTimestamps = arrayOf<String>(
        "Sep 3rd, 2020",
        "Sep 15th, 2020",
        "Sep 24th, 2020",
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instructorStatisticsViewModel =
            ViewModelProviders.of(this).get(InstructorStatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_statistics, container, false)
        val textView: TextView = root.findViewById(R.id.text_instructor_statistics_title)
        instructorStatisticsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // init Main Claims ListView
        val gamesListView = view.findViewById(R.id.listView_instructor_statistics) as ListView
        val gameListAdapter =
            InstructorStatsListAdapter(
                view.context as Activity,
                gameIds, gameDateTimestamps
            )

        gamesListView.adapter = gameListAdapter
    }
}