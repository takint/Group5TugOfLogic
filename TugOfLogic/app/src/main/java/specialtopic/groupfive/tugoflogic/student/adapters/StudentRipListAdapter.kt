package specialtopic.groupfive.tugoflogic.student.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R


class StudentRipListAdapter(
    private val context: Context?,
    private val studentRips: ArrayList<String>
) : RecyclerView.Adapter<StudentRipListAdapter.StudentRipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_student_rip_item, parent, false)

        return StudentRipViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StudentRipViewHolder, position: Int) {
        holder.etStudentRip.text = studentRips[position]

        holder.btnPutToBoard.setOnClickListener {
            Toast.makeText(context, studentRips[position], Toast.LENGTH_SHORT).show()
        }

        holder.btnEditRip.setOnClickListener {
            Toast.makeText(context, studentRips[position], Toast.LENGTH_SHORT).show()
        }

        holder.btnDeleteRip.setOnClickListener {
            Toast.makeText(context, studentRips[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return studentRips.size
    }

    class StudentRipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etStudentRip: TextView = itemView.findViewById<TextView>(R.id.etStudentRip)
        val btnPutToBoard: Button = itemView.findViewById<Button>(R.id.btnPutToBoard)
        val btnEditRip: ImageView = itemView.findViewById<ImageView>(R.id.btnEditRip)
        val btnDeleteRip: ImageView = itemView.findViewById<ImageView>(R.id.btnDeleteRip)
    }
}