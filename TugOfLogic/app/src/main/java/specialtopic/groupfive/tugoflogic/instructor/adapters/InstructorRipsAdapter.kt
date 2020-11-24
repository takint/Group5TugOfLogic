package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.InstructorDiscussionManagerActivity
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.InstructorReasonsInPlayActivity
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay

/**
 * Adapter for Choosing Main Claim
 */
class InstructorRipsAdapter(private val mRips: ArrayList<ReasonInPlay>) :
    RecyclerView.Adapter<InstructorRipsAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val id = itemView.findViewById<TextView>(R.id.textView_instructor_rip_id)
        val content = itemView.findViewById<TextView>(R.id.textView_instructor_rip_content)
        val author = itemView.findViewById<TextView>(R.id.textView_instructor_rip_author)
        val voteButton = itemView.findViewById<TextView>(R.id.btn_instructor_rip_vote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val chooseRipView = inflater.inflate(R.layout.layout_instructor_rip_item, parent, false)

        return ViewHolder(chooseRipView)
    }

    override fun getItemCount(): Int {
        return mRips.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rip: ReasonInPlay = mRips.get(position)

        val txtViewId = holder.id
        val txtViewContent = holder.content
        val txtViewAuthor = holder.author

        txtViewId.text = rip.ripId.toString()
        txtViewContent.text = rip.reasonStatement
        txtViewAuthor.text = rip.studentId.toString() // TODO: should take student name instead
    }
}