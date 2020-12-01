package specialtopic.groupfive.tugoflogic.instructor.ui.instructorstatistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game_statistics.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.adapters.InstructorChooseMCAdapter
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.Observer

class GameStatisticsActivity : AppCompatActivity() {

    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_statistics)

        // Init data repository for using on this activity
        tugDataRepo = application?.let { DataRepository(it) }!!
        val gameId: Int = intent.getIntExtra("gameId", 0)

        CoroutineScope(Dispatchers.IO).launch {
            tugDataRepo.getGameByIdFromService(application, gameId, onComplete = {
                val gameData = it;
                txt_game_stats_id.text = gameData?.gameId.toString()
                txt_game_stats_date_time.text = gameData?.startTime.toString()
                txt_game_stats_players.text = gameData?.numOfPlayer.toString()
            })
        }
    }
}