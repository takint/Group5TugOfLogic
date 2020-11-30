package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_instructor_mainclaim.*
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
        // Fragment Root
        val root = inflater.inflate(R.layout.fragment_instructor_mainclaim, container, false)

//        // Fragment title
//        val textView: TextView = root.findViewById(R.id.text_instructor_main_claim_title)
//        instructorMainClaimViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

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
                mMainClaimsRVAdapter = InstructorMainClaimsRVAdapter(listMainClaims, tugDataRepo, this.requireActivity().application)
                mMainClaimsRV.adapter = mMainClaimsRVAdapter
                mMainClaimsRV.layoutManager = mLayoutManager
            })
        }

        // Add Floating Action Button - FAB
        btnFabInstructor.setOnClickListener(View.OnClickListener {
            Log.i(TAG, "onViewCreated: Add Button Clicked! Go to NewMainClaim Activity")
            val addNewMainClaimIntent = Intent(activity, AddNewMainClaimActivity::class.java).apply { }
            startActivity(addNewMainClaimIntent)

            // Skip the MainClaim Fragment and go back to the parent activity
            activity?.onBackPressed()
        })

        // Refresh Button
        btnRefreshMainClaims.setOnClickListener(View.OnClickListener {
            mMainClaimsRVAdapter.notifyDataSetChanged()

            val ft: FragmentTransaction = parentFragmentManager.beginTransaction()
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false)
            }
            ft.detach(this).attach(this).commit()
        })
    }
}