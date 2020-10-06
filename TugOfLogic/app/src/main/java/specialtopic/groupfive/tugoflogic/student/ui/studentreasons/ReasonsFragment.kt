package specialtopic.groupfive.tugoflogic.student.ui.studentreasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.student.adapters.ParticipateRipListAdapter

class ReasonsFragment : Fragment() {

    //private lateinit var reasonsViewModel: ReasonsViewModel
    private lateinit var participateRipListView: RecyclerView
    private lateinit var participateRipsAdapter: ParticipateRipListAdapter
    private lateinit var tugDataRepo: DataRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //reasonsViewModel = ViewModelProviders.of(this).get(ReasonsViewModel::class.java)
        //reasonsViewModel.text.observe(viewLifecycleOwner, Observer {
        //})
        val root = inflater.inflate(R.layout.fragment_student_reasons, container, false)
        tugDataRepo = activity?.application?.let { DataRepository(it) }!!

        setupPartRipListPage(root)

        return root
    }

    private fun setupPartRipListPage(container: View) {
        activity?.let { fragmentActivity ->
            tugDataRepo.getRipsData().observe(fragmentActivity, Observer {
                val studentCurrentRips: ArrayList<ReasonInPlay> = ArrayList<ReasonInPlay>(it)
                participateRipListView = container.findViewById(R.id.lstParticipateRipList)
                participateRipListView.setHasFixedSize(true)
                participateRipListView.layoutManager = LinearLayoutManager(container.context)
                participateRipsAdapter = ParticipateRipListAdapter(this.context, studentCurrentRips)
                participateRipListView.adapter = participateRipsAdapter
            })
        }
    }
}