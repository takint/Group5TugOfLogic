package specialtopic.groupfive.tugoflogic.instructor.ui.instructorgame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_instructor_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.ChooseMainClaimActivity
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.GameRoomActivity
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.InstructorWaitingVoteActivity
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.utilities.GAME_ID_KEY

class InstructorGameFragment : Fragment() {

    private lateinit var instructorGameViewModel: InstructorGameViewModel
    private lateinit var tugDataRepo: DataRepository
    private var currentGameId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        instructorGameViewModel =
            ViewModelProviders.of(this).get(InstructorGameViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_game, container, false)

        tugDataRepo = DataRepository(requireActivity().application)
        tugDataRepo.getGamesData().observe(requireActivity(), {
            val game = it.find { game -> game.isCurrent }
            if (game != null) {
                currentGameId = game.gameId!!
                btn_current_game.isEnabled = true
            } else {
                btn_current_game.isEnabled = false
            }
        })

        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getGamesFromService()
        }

        val btnCreateNewGame = root.findViewById<Button>(R.id.btn_create_new_game)
        btnCreateNewGame.setOnClickListener {
            val intent = Intent(context, GameRoomActivity::class.java)
            startActivity(intent)
        }

        val btnCurrentGame = root.findViewById<Button>(R.id.btn_current_game)
        btnCurrentGame.setOnClickListener {
            if (currentGameId > 0) {
                val chooseMCIntent = Intent(context, ChooseMainClaimActivity::class.java)
                chooseMCIntent.putExtra(GAME_ID_KEY, currentGameId)
                startActivity(chooseMCIntent)
            }
        }

        return root
    }
}