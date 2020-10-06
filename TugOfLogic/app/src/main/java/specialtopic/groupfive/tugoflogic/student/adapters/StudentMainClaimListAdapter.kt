package specialtopic.groupfive.tugoflogic.student.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

class StudentMainClaimListAdapter(
    private val context: Context?,
    private val studentMCs: ArrayList<MainClaim>
) : RecyclerView.Adapter<StudentMainClaimListAdapter.StudentMainClaimViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentMainClaimViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_student_mc_item, parent, false)

        return StudentMainClaimViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StudentMainClaimViewHolder, position: Int) {
        holder.tvStudentMc.text = studentMCs[position].statement

        holder.btnVoteDiscuss.setOnClickListener {
            Toast.makeText(context, studentMCs[position].mainClaimId.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        holder.btnVoteDrop.setOnClickListener {
            Toast.makeText(context, studentMCs[position].mainClaimId.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        holder.radioLogicSide.setOnCheckedChangeListener { _, checkedId ->
            Toast.makeText(context, checkedId.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return studentMCs.size
    }

    class StudentMainClaimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStudentMc: TextView = itemView.findViewById<TextView>(R.id.tvStudentVoteMainClaim)
        val btnVoteDiscuss: Button = itemView.findViewById<Button>(R.id.btnVoteToDiscuss)
        val btnVoteDrop: Button = itemView.findViewById<Button>(R.id.btnVoteToDrop)
        val radioLogicSide: RadioGroup =
            itemView.findViewById<RadioGroup>(R.id.radStudentMCLogicSide)
    }
}