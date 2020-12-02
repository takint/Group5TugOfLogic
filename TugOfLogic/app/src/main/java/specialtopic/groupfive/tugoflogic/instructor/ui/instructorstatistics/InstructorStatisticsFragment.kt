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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorStatsRVAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame

class InstructorStatisticsFragment : Fragment() {

    private lateinit var tugDataRepo: DataRepository
    private lateinit var mStatsRV: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mStatsRVAdapter: RecyclerView.Adapter<InstructorStatsRVAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_instructor_statistics, container, false)

        mStatsRV = root.findViewById(R.id.rv_instructor_statistics)
        mLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mStatsRV.layoutManager = mLayoutManager

        // Init data repository for using on this fragment
        tugDataRepo = DataRepository(requireActivity().application)

        tugDataRepo.getGamesHistoryData().observe(requireActivity(), {
            val listGames: ArrayList<TugGame> = ArrayList(it)
            mStatsRVAdapter = InstructorStatsRVAdapter(
                listGames,
                tugDataRepo,
                this.requireActivity().application
            )
            mStatsRV.adapter = mStatsRVAdapter
        })

        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getGamesHistoryFromService()
        }

        return root
    }
}