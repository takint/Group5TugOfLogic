package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.withContext
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim.AddNewMainClaimActivity
import specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim.EditMainClaimActivity
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

class InstructorMainClaimsRVAdapter(
    private val mMainClaims: ArrayList<MainClaim>,
    private val tugDataRepo: DataRepository,
    private val app: Application
) : RecyclerView.Adapter<InstructorMainClaimsRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.textView_main_claim_id)
        var mc: TextView = itemView.findViewById(R.id.textView_main_claim_content)
        var btnEdit: AppCompatImageButton = itemView.findViewById(R.id.btn_main_claim_edit)
        var btnDelete: AppCompatImageButton = itemView.findViewById(R.id.btn_main_claim_delete)

        init {
            btnEdit.setOnClickListener {
                val position: Int = adapterPosition

                // Implement Edit Button (Update MainClaim)
                val currentMC = mMainClaims[position]

                // Put extra and start EditMainClaim Activity
                val context = itemView.context
                val editMainClaimIntent =
                    Intent(context, EditMainClaimActivity::class.java).apply { }
                editMainClaimIntent.putExtra("mainClaimId", currentMC.mainClaimId)
                context.startActivity(editMainClaimIntent)
            }

            btnDelete.setOnClickListener {
                val position: Int = adapterPosition
                val currentMC = mMainClaims[position]
                tugDataRepo.deleteMainClaim(app, currentMC.mainClaimId)
                mMainClaims.removeAt(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom view from xml layout file
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_instructor_mainclaim_item, parent, false)

        // Return the view holder
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainClaim: MainClaim = mMainClaims[position]
        holder.id.text = (position + 1).toString()
        holder.mc.text = mainClaim.statement
    }

    override fun getItemCount(): Int {
        return mMainClaims.size
    }
}