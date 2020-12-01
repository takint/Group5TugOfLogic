package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.IMainClaim
import specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim.EditMainClaimActivity
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper
import java.lang.ClassCastException

class MainClaimDissussionAdapter(
    private val mMainClaims: ArrayList<MainClaim>,
    context: Context
//    private val tugDataRepo: DataRepository,
//    private val app: Application
):RecyclerView.Adapter<MainClaimDissussionAdapter.ViewHolder>() {
    lateinit var iMainClaim: IMainClaim
    init {
        val context = context;
        if(context is IMainClaim){
            iMainClaim = context as IMainClaim
        }else{
            throw ClassCastException()
        }
    }

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
            Toast.makeText(holder.itemView.context, "${mainClaim.statement} is selected to discuss", Toast.LENGTH_SHORT).show()
            iMainClaim.setCurrentMainClaim(mainClaim)
            NetworkHelper.mSocket.emit("setCurrentMainClaim", mainClaim.mainClaimId)
        })
    }

    override fun getItemCount(): Int {
        return mMainClaims.size
    }
}