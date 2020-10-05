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

class ParticipateRipListAdapter(
    private val context: Context?,
    private val participateRips: ArrayList<String>
) : RecyclerView.Adapter<ParticipateRipListAdapter.ParticipateRipViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParticipateRipListAdapter.ParticipateRipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_student_reason_item, parent, false)

        return ParticipateRipListAdapter.ParticipateRipViewHolder(listItem)
    }

    override fun onBindViewHolder(
        holder: ParticipateRipListAdapter.ParticipateRipViewHolder,
        position: Int
    ) {
        holder.etPartName.text = participateRips[position]
        holder.etPartReason.text = participateRips[position]

        holder.btnAgreePatRip.setOnClickListener {
            Toast.makeText(context, participateRips[position], Toast.LENGTH_SHORT).show()
        }

        holder.btnDisagreePatRip.setOnClickListener {
            Toast.makeText(context, participateRips[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return participateRips.size
    }

    class ParticipateRipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etPartName: TextView = itemView.findViewById<TextView>(R.id.tvPaticipateName)
        val etPartReason: TextView = itemView.findViewById<TextView>(R.id.tvPaticipateRip)
        val btnAgreePatRip: Button = itemView.findViewById<Button>(R.id.btnPRAgree)
        val btnDisagreePatRip: Button = itemView.findViewById<Button>(R.id.btnPRDisagree)
    }
}