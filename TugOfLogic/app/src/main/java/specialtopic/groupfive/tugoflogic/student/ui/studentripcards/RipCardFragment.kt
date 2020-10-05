package specialtopic.groupfive.tugoflogic.student.ui.studentripcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import specialtopic.groupfive.tugoflogic.R

class RipCardFragment : Fragment() {

    private lateinit var ripCardViewModel: RipCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ripCardViewModel =
            ViewModelProviders.of(this).get(RipCardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_student_ripcards, container, false)

        ripCardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}