package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame

class InstructorStatsRVAdapter(
    private val mGames: ArrayList<TugGame>
) : RecyclerView.Adapter<InstructorStatsRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.textView_statistics_game_id)
        var timeStamp: TextView = itemView.findViewById(R.id.textView_statistics_game_date_time)

        init {
            itemView.setOnClickListener {
                // TODO: implement item click here
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You clicked on item $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom view from xml layout file
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_instructor_statistics_item, parent, false)

        // Return the view holder
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game: TugGame = mGames[position]
        holder.id.text = game.gameId.toString()
        holder.timeStamp.text = game.startTime.toString()
    }

    override fun getItemCount(): Int {
        return mGames.size;
    }
}