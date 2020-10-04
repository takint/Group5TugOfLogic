package specialtopic.groupfive.tugoflogic.student.ui.studentreasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import specialtopic.groupfive.tugoflogic.R

class ReasonsFragment : Fragment() {

    private lateinit var reasonsViewModel: ReasonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reasonsViewModel =
            ViewModelProviders.of(this).get(ReasonsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_student_reasons, container, false)
        val textView: TextView = root.findViewById(R.id.text_student_reasons)
        reasonsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}