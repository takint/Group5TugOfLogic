package specialtopic.groupfive.tugoflogic.student.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.student.ui.StudentWaitingRoomActivity
import specialtopic.groupfive.tugoflogic.utilities.ROOM_ID_KEY
import specialtopic.groupfive.tugoflogic.utilities.USER_NAME_KEY

class ChooseGameAdapter(private val gameRooms: ArrayList<String>, private val username: String) :
    RecyclerView.Adapter<ChooseGameAdapter.ViewHolder>() {
    private val mUsername = username

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtGameID: TextView = itemView.findViewById<TextView>(R.id.txtGameID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.student_gameroom_item, parent, false)

        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return gameRooms.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roomID = gameRooms[position]
        holder.txtGameID.text =
            String.format(holder.itemView.context.getString(R.string.game_room_template, roomID))

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StudentWaitingRoomActivity::class.java)
            intent.putExtra(ROOM_ID_KEY, roomID)
            intent.putExtra(USER_NAME_KEY, mUsername)
            holder.itemView.context.startActivity(intent)
        }
    }
}