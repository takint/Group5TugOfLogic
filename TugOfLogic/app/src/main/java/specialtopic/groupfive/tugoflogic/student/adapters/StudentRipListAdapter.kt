package specialtopic.groupfive.tugoflogic.student.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper


class StudentRipListAdapter(
    private val context: Context?,
    private val tugDataRepo: DataRepository,
    private val studentRips: ArrayList<ReasonInPlay>
) : RecyclerView.Adapter<StudentRipListAdapter.StudentRipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_student_rip_item, parent, false)

        return StudentRipViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StudentRipViewHolder, position: Int) {
        holder.etStudentRip.setText(studentRips[position].reasonStatement)

        holder.btnPutToBoard.setOnClickListener {
            studentRips[position].reasonStatement = holder.etStudentRip.text.toString()
            studentRips[position].description = holder.etStudentRip.text.toString()
            studentRips[position].logicSide = "neutral"

            if (context != null) {
                tugDataRepo.addNewRiP(
                    context.applicationContext as Application,
                    studentRips[position]
                )
            }

            NetworkHelper.mSocket.emit("newRipFromPlayer", studentRips[position].reasonStatement)
        }

        holder.btnEditRip.setOnClickListener {
            holder.etStudentRip.isEnabled = true
        }

        holder.btnDeleteRip.setOnClickListener {
            if (studentRips[position].mainClaimId > 0) {
                if (context != null) {
                    tugDataRepo.deleteRiP(
                        context.applicationContext as Application,
                        studentRips[position]
                    )

                    NetworkHelper.mSocket.emit("newRipFromPlayer", studentRips[position].reasonStatement)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return studentRips.size
    }

    class StudentRipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etStudentRip: EditText = itemView.findViewById(R.id.etStudentRip)
        val btnPutToBoard: Button = itemView.findViewById(R.id.btnPutToBoard)
        val btnEditRip: ImageView = itemView.findViewById(R.id.btnEditRip)
        val btnDeleteRip: ImageView = itemView.findViewById(R.id.btnDeleteRip)
    }
}