package specialtopic.groupfive.tugoflogic.student.ui.studentripcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.student.adapters.StudentRipListAdapter


class RipCardFragment : Fragment() {

    private lateinit var studentRipListView: RecyclerView
    private lateinit var studentRipsAdapter: StudentRipListAdapter
    private lateinit var tugDataRepo: DataRepository
    private var studentCurrentRips = ArrayList<ReasonInPlay>()
    private var placeholderCard = ReasonInPlay(
        0, 153354, 2,
        "", "", ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_ripcards, container, false)
        tugDataRepo = DataRepository(requireActivity().application)
        studentRipListView = root.findViewById(R.id.lstPlayerRipList)
        studentRipListView.setHasFixedSize(true)
        studentRipListView.layoutManager = LinearLayoutManager(root.context)
        studentCurrentRips.add(placeholderCard)

        tugDataRepo.getRipsData().observe(requireActivity(), {
            studentCurrentRips = ArrayList(it)
            studentCurrentRips.add(placeholderCard)
            studentRipsAdapter = StudentRipListAdapter(context, tugDataRepo, studentCurrentRips)
            studentRipListView.adapter = studentRipsAdapter
        })

        loadRiPData()
        return root
    }

    private fun loadRiPData() {
        CoroutineScope(Dispatchers.IO).launch {
             tugDataRepo.getRiPDataByUser("TestUser")
        }
    }
}