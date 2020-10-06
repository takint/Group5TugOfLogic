package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.tempUser

class StudentsAdapter (private  val mUser: ArrayList<tempUser>) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>(){
    inner class  ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.txt_GameRoom_UserName)
        val tick = itemView.findViewById<ImageView>(R.id.img_GameRoom_Tick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.instructor_gameroom_item_user, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: tempUser = mUser.get(position)

        val textView = holder.nameTextView
        textView.setText(user.name)
        val tickView = holder.tick

    }

    override fun getItemCount(): Int {
        return mUser.size
    }
}