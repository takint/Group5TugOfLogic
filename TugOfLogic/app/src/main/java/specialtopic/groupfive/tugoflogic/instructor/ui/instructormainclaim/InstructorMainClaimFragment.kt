package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

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


class InstructorMainClaimFragment : Fragment() {

    private lateinit var instructorMainClaimViewModel: InstructorMainClaimViewModel

    // Dummy Data - Delete when implement database
    private val mainClaimIds = arrayOf<Int>(1, 2, 3, 4, 5)
    private val mainClaimContents = arrayOf<String>(
        "Main Claim 1 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
        "Main Claim 2 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
        "Main Claim 3 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
        "Main Claim 4 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
        "Main Claim 5 xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instructorMainClaimViewModel =
            ViewModelProviders.of(this).get(InstructorMainClaimViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_mainclaim, container, false)
        val textView: TextView = root.findViewById(R.id.text_instructor_main_claim_title)
        instructorMainClaimViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // init Main Claims ListView
        val mainClaimsListView = view.findViewById(R.id.listView_instructor_main_claims) as ListView
        val mainClaimsListAdapter =
            InstructorMainClaimsListAdapter(
                view.context as Activity,
                mainClaimIds,
                mainClaimContents
            )

        mainClaimsListView.adapter = mainClaimsListAdapter
    }
}