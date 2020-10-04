package specialtopic.groupfive.tugoflogic.student.ui.studentboardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import specialtopic.groupfive.tugoflogic.R

class GameBoardFragment : Fragment() {

    private lateinit var gameBoardViewModel: GameBoardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        gameBoardViewModel =
                ViewModelProviders.of(this).get(GameBoardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_student_gameboard, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        gameBoardViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
}