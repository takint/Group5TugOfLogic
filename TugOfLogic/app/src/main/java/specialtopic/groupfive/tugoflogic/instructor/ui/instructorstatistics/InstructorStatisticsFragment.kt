package specialtopic.groupfive.tugoflogic.instructor.ui.instructorstatistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import specialtopic.groupfive.tugoflogic.R

class InstructorStatisticsFragment : Fragment() {

    private lateinit var instructorStatisticsViewModel: InstructorStatisticsViewModel

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        instructorStatisticsViewModel =
            ViewModelProviders.of(this).get(InstructorStatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_statistics, container, false)
        val textView: TextView = root.findViewById(R.id.text_instructor_statistics)
        instructorStatisticsViewModel.text.observe(viewLifecycleOwner, Observer {
          textView.text = it
        })
        return root
    }
}