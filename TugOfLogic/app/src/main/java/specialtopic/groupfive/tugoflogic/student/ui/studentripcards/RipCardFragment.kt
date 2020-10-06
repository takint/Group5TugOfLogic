package specialtopic.groupfive.tugoflogic.student.ui.studentripcards

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
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.student.adapters.StudentRipListAdapter


class RipCardFragment : Fragment() {

    //private lateinit var ripCardViewModel: RipCardViewModel
    private lateinit var studentRipListView: RecyclerView
    private lateinit var studentRipsAdapter: StudentRipListAdapter
    private lateinit var tugDataRepo: DataRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ripCardViewModel = ViewModelProviders.of(this).get(RipCardViewModel::class.java)
        //ripCardViewModel.text.observe(viewLifecycleOwner, Observer {
        //})
        val root = inflater.inflate(R.layout.fragment_student_ripcards, container, false)
        tugDataRepo = activity?.application?.let { DataRepository(it) }!!

        setupStudentRipListPage(root)

        return root
    }

    private fun setupStudentRipListPage(container: View) {
        activity?.let { fragmentActivity ->
            tugDataRepo.getRipsData().observe(fragmentActivity, Observer {
                val studentCurrentRips: ArrayList<ReasonInPlay> = ArrayList<ReasonInPlay>(it)
                studentRipListView = container.findViewById(R.id.lstPlayerRipList)
                studentRipListView.setHasFixedSize(true)
                studentRipListView.layoutManager = LinearLayoutManager(container.context)
                studentRipsAdapter = StudentRipListAdapter(this.context, studentCurrentRips)
                studentRipListView.adapter = studentRipsAdapter
            })
        }
    }
}