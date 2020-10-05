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
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.GameRoomActivity
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository

class InstructorGameFragment : Fragment() {

    private lateinit var instructorGameViewModel: InstructorGameViewModel
    private lateinit var tugDataRepo: DataRepository

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        // Init data repository for using on this fragment
        tugDataRepo = activity?.application?.let { DataRepository(it) }!!

        instructorGameViewModel =
            ViewModelProviders.of(this).get(InstructorGameViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructor_game, container, false)
        val textView: TextView = root.findViewById(R.id.text_instructor_game)
        instructorGameViewModel.text.observe(viewLifecycleOwner, Observer {
          textView.text = it
        })

        val btnCreateNewGame: Button = root.findViewById(R.id.btn_create_new_game)
        btnCreateNewGame.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, GameRoomActivity::class.java).apply{}
            startActivity(intent)
        })

        return root
    }
}