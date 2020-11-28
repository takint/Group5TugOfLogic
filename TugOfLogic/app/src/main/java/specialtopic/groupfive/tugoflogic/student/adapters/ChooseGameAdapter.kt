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

class ChooseGameAdapter (private  val gameRooms: ArrayList<String>, private val username: String): RecyclerView.Adapter<ChooseGameAdapter.ViewHolder>(){
    val mUsername = username
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtGameID = itemView.findViewById<TextView>(R.id.txtGameID)
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
        val roomID: String = gameRooms.get(position)
        holder.txtGameID.setText("Game Room ID: ${roomID}")

        holder.itemView.setOnClickListener(View.OnClickListener {

            val intent = Intent(holder.itemView.context, StudentWaitingRoomActivity::class.java)
            intent.putExtra("roomID", roomID)
            intent.putExtra("username", mUsername)
            holder.itemView.context.startActivity(intent)
        })
    }
}