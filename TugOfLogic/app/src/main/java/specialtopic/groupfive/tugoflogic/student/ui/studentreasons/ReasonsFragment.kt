package specialtopic.groupfive.tugoflogic.student.ui.studentreasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.emitter.Emitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.student.adapters.ParticipateRipListAdapter
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper

class ReasonsFragment : Fragment() {

    private lateinit var participateRipListView: RecyclerView
    private lateinit var participateRipsAdapter: ParticipateRipListAdapter
    private lateinit var tugDataRepo: DataRepository
    private var studentCurrentRips = ArrayList<ReasonInPlay>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_reasons, container, false)
        tugDataRepo = DataRepository(requireActivity().application)
        participateRipListView = root.findViewById(R.id.lstParticipateRipList)
        participateRipListView.setHasFixedSize(true)
        participateRipListView.layoutManager = LinearLayoutManager(root.context)

        tugDataRepo.getGameRipData().observe(requireActivity(), {
            studentCurrentRips = ArrayList(it)
            participateRipsAdapter = ParticipateRipListAdapter(this.context, studentCurrentRips)
            participateRipListView.adapter = participateRipsAdapter
        })

        loadRipData()
        NetworkHelper.mSocket.on("newRipFromPlayer", onNewRipComing)
        return root
    }

    private fun loadRipData() {
        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getRiPData()
        }
    }

    private val onNewRipComing = Emitter.Listener {
        loadRipData()
    }
}