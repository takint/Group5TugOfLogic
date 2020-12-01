package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim.EditMainClaimActivity
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

class MainClaimDissussionAdapter(
    private val mMainClaims: ArrayList<String>,
//    private val tugDataRepo: DataRepository,
//    private val app: Application
):RecyclerView.Adapter<MainClaimDissussionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mc: TextView = itemView.findViewById(R.id.txt_discussingMainClaim)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.instructor_gameroom_mc_discussion_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainClaim: MainClaim = mMainClaims[position]
        holder.mc.text = mainClaim.statement

        holder.itemView.setOnClickListener(View.OnClickListener {
            //selectMainClaimToDiscuss()
        })
    }

    override fun getItemCount(): Int {
        return mMainClaims.size
    }
}