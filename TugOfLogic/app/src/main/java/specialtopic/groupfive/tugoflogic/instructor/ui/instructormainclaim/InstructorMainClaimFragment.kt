package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorMainClaimsRVAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim


class InstructorMainClaimFragment : Fragment() {
    private lateinit var tugDataRepo: DataRepository
    private lateinit var mMainClaimsRV: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mMainClaimsRVAdapter: RecyclerView.Adapter<InstructorMainClaimsRVAdapter.ViewHolder>
    private lateinit var instructorMainClaimViewModel: InstructorMainClaimViewModel

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
        Log.i(TAG, "onViewCreated: Main Claim Fragment")

        activity?.let { fragmentActivity ->
            tugDataRepo.getMainClaimData().observe(fragmentActivity, Observer {
                val listMainClaims = ArrayList<MainClaim>(it)

                // Recycler View
                mMainClaimsRV = view.findViewById(R.id.rv_instructor_main_claims) as RecyclerView
                mLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                mMainClaimsRVAdapter = InstructorMainClaimsRVAdapter(listMainClaims)
                mMainClaimsRV.adapter = mMainClaimsRVAdapter
                mMainClaimsRV.layoutManager = mLayoutManager
            })
        }
    }
}