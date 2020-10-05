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
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay


class InstructorMainClaimFragment : Fragment() {

    private lateinit var instructorMainClaimViewModel: InstructorMainClaimViewModel
    private lateinit var tugDataRepo: DataRepository

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

        // Init data repository for using on this fragment
        tugDataRepo = activity?.application?.let { DataRepository(it) }!!

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // init Main Claims ListView
        activity?.let { fragmentActivity ->
            tugDataRepo.getMainClaimData().observe(fragmentActivity, Observer {
                val lstMainClaims: ArrayList<MainClaim> = ArrayList<MainClaim>(it)
                val mainClaimsListView = view.findViewById(R.id.listView_instructor_main_claims) as ListView
                val mainClaimsListAdapter =
                    InstructorMainClaimsListAdapter(
                        view.context as Activity,
                        lstMainClaims
                    )

                mainClaimsListView.adapter = mainClaimsListAdapter
            })
        }
    }
}