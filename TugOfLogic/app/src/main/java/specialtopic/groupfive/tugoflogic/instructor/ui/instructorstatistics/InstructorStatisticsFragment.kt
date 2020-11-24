package specialtopic.groupfive.tugoflogic.instructor.ui.instructorstatistics

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorStatsRVAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame

class InstructorStatisticsFragment : Fragment() {

    private lateinit var tugDataRepo: DataRepository
    private lateinit var mStatsRV: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mStatsRVAdapter: RecyclerView.Adapter<InstructorStatsRVAdapter.ViewHolder>
    private lateinit var instructorStatisticsViewModel: InstructorStatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instructorStatisticsViewModel =
            ViewModelProviders.of(this).get(InstructorStatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_statistics, container, false)
        val textView: TextView = root.findViewById(R.id.text_instructor_statistics_title)
        instructorStatisticsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        // Init data repository for using on this fragment
        tugDataRepo = activity?.application?.let { DataRepository(it) }!!

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(ContentValues.TAG, "onViewCreated: Statistics Fragment")

        activity?.let { fragmentActivity ->
            tugDataRepo.getGamesData().observe(fragmentActivity, Observer {
                val lstGames: ArrayList<TugGame> = ArrayList(it)

                // Recycler View
                mStatsRV = view.findViewById(R.id.rv_instructor_statistics) as RecyclerView
                mLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                mStatsRVAdapter = InstructorStatsRVAdapter(lstGames)
                mStatsRV.adapter = mStatsRVAdapter
                mStatsRV.layoutManager = mLayoutManager
            })
        }

    }
}