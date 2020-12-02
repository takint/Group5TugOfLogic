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
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.roomdb.entities.VoteTicket

class ParticipateRipListAdapter(
    private val context: Context?,
    private val tugDataRepo: DataRepository,
    private val currentUser: String,
    private val currentGame: Int,
    private val currentMc: Int,
    private val participateRips: ArrayList<ReasonInPlay>
) : RecyclerView.Adapter<ParticipateRipListAdapter.ParticipateRipViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParticipateRipListAdapter.ParticipateRipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.layout_student_reason_item, parent, false)

        return ParticipateRipViewHolder(listItem)
    }

    override fun onBindViewHolder(
        holder: ParticipateRipViewHolder,
        position: Int
    ) {
        holder.etPartName.text = String.format("StudentId: %d", participateRips[position].studentId)
        holder.etPartReason.text = participateRips[position].reasonStatement



        participateRips[position].mainClaimId
        var newVotes = VoteTicket(
            voteId = 0,
            gameId = 1,
            userId = 2,
            mainClaimId = participateRips[position].mainClaimId,
            RipId = 2,
            statementToVote = "",
            voteSide = participateRips[position].logicSide
        )

        holder.btnAgreePatRip.setOnClickListener {
            newVotes.voteSide = "agree"
            newVotes.statementToVote = "Agree with ${participateRips[position].reasonStatement}"

            Toast.makeText(context, participateRips[position].description, Toast.LENGTH_SHORT)
                .show()
        }

        holder.btnDisagreePatRip.setOnClickListener {
            newVotes.voteSide = "disagree"
            newVotes.statementToVote = "Disagree with ${participateRips[position].reasonStatement}"



            Toast.makeText(context, participateRips[position].description, Toast.LENGTH_SHORT)
                .show()
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