package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

class InstructorMainClaimsRVAdapter(
    private val mMainClaims: ArrayList<MainClaim>
) : RecyclerView.Adapter<InstructorMainClaimsRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id : TextView = itemView.findViewById(R.id.textView_main_claim_id)
        var mc : TextView = itemView.findViewById(R.id.textView_main_claim_content)
        var btnEdit: AppCompatImageButton = itemView.findViewById(R.id.btn_main_claim_edit)
        var btnDelete: AppCompatImageButton = itemView.findViewById(R.id.btn_main_claim_delete)

        init {
            btnEdit.setOnClickListener{
                val position: Int = getAdapterPosition()
                Toast.makeText(itemView.context, "You clicked Edit on item $position" , Toast.LENGTH_SHORT).show()

                // TODO: implement Edit Button (Update MainClaim)
            }

            btnDelete.setOnClickListener{
                val position: Int = getAdapterPosition()
                Toast.makeText(itemView.context, "You clicked Delete on item $position" , Toast.LENGTH_SHORT).show()

                // TODO: implement Delete Button (Delete MainClaim)
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
        holder.id.text = mainClaim.mainClaimId.toString()
        holder.mc.text = mainClaim.statement
    }

    override fun getItemCount(): Int {
        return mMainClaims.size
    }
}