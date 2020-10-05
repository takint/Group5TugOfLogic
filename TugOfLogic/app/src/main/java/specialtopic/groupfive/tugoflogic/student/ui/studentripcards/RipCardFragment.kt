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
import specialtopic.groupfive.tugoflogic.student.adapters.StudentRipListAdapter


class RipCardFragment : Fragment() {

    private lateinit var ripCardViewModel: RipCardViewModel
    private lateinit var studentRipListView: RecyclerView
    private lateinit var studentRipsAdapter: StudentRipListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ripCardViewModel = ViewModelProviders.of(this).get(RipCardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_student_ripcards, container, false)

        setupStudentRipListPage(root)

        ripCardViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    private fun setupStudentRipListPage(container: View) {
        // TODO: get the real data to here
        val studentCurrentRips: ArrayList<String> = arrayListOf("Reason 1", "Reason 2", "Reason 3")

        studentRipListView = container.findViewById(R.id.lstPlayerRipList)
        studentRipListView.setHasFixedSize(true)
        studentRipListView.layoutManager = LinearLayoutManager(container.context)
        studentRipsAdapter = StudentRipListAdapter(this.context, studentCurrentRips)
        studentRipListView.adapter = studentRipsAdapter
    }
}