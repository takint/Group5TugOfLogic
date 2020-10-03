package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import specialtopic.groupfive.tugoflogic.R


class InstructorMainClaimFragment : Fragment() {

    private lateinit var instructorMainClaimViewModel: InstructorMainClaimViewModel

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        instructorMainClaimViewModel =
            ViewModelProviders.of(this).get(InstructorMainClaimViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_mainclaim, container, false)
        val textView: TextView = root.findViewById(R.id.text_student_cards)
        instructorMainClaimViewModel.text.observe(viewLifecycleOwner, Observer {
          textView.text = it
        })
        return root
    }
}