package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.student.Student

class StudentsAdapter (private  val mStudent: List<Student>) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>(){
    inner class  ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.txt_GameRoom_StudentName)
        val tick = itemView.findViewById<ImageView>(R.id.img_GameRoom_Tick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.item_student, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student: Student = mStudent.get(position)

        val textView = holder.nameTextView
        textView.setText(student.name)
        val tickView = holder.tick
        if(!student.isOnline){
            tickView.visibility = View.INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        return mStudent.size
    }
}