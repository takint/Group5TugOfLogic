package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_instructor_mainclaim.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorMainClaimsRVAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim


class InstructorMainClaimFragment : Fragment() {
    private lateinit var tugDataRepo: DataRepository
    private lateinit var mMainClaimsRV: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mMainClaimsRVAdapter: InstructorMainClaimsRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_instructor_mainclaim, container, false)
        tugDataRepo = DataRepository(requireActivity().application)
        mMainClaimsRV = root.findViewById(R.id.rv_instructor_main_claims)
        mLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mMainClaimsRV.layoutManager = mLayoutManager

        tugDataRepo.getMainClaimData().observe(requireActivity(), Observer {
            val listMainClaims = ArrayList<MainClaim>(it)

            mMainClaimsRVAdapter = InstructorMainClaimsRVAdapter(
                listMainClaims,
                tugDataRepo,
                requireActivity().application
            )
            mMainClaimsRV.adapter = mMainClaimsRVAdapter

        })

        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getMCsFromService(requireActivity().application)
        }

        val fabNewMc = root.findViewById<FloatingActionButton>(R.id.btnFabInstructor)

        fabNewMc.setOnClickListener {
            val addNewMainClaimIntent = Intent(activity, AddNewMainClaimActivity::class.java)
            startActivity(addNewMainClaimIntent)
        }

        return root
    }
}