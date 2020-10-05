package specialtopic.groupfive.tugoflogic.student.ui.studentreasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_student_reasons.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.student.adapters.ParticipateRipListAdapter
import specialtopic.groupfive.tugoflogic.student.adapters.StudentRipListAdapter

class ReasonsFragment : Fragment() {

    private lateinit var reasonsViewModel: ReasonsViewModel
    private lateinit var participateRipListView: RecyclerView
    private lateinit var participateRipsAdapter: ParticipateRipListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reasonsViewModel = ViewModelProviders.of(this).get(ReasonsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_student_reasons, container, false)

        setupPartRipListPage(root)

        reasonsViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    private fun setupPartRipListPage(container: View) {
        // TODO: get the real data to here
        val studentCurrentRips: ArrayList<String> = arrayListOf("Reason 1", "Reason 2", "Reason 3")

        participateRipListView = container.findViewById(R.id.lstParticipateRipList)
        participateRipListView.setHasFixedSize(true)
        participateRipListView.layoutManager = LinearLayoutManager(container.context)
        participateRipsAdapter = ParticipateRipListAdapter(this.context, studentCurrentRips)
        participateRipListView.adapter = participateRipsAdapter
    }
}