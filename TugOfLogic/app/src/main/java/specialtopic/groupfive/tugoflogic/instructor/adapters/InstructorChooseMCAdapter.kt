package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.IMainClaim
import specialtopic.groupfive.tugoflogic.instructor.ui.gameroom.InstructorSetTime
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import java.lang.ClassCastException

/**
 * Adapter for Choosing Main Claim
 */
class InstructorChooseMCAdapter(private val mMCs: HashMap<MainClaim, Boolean>, context: Context): RecyclerView.Adapter<InstructorChooseMCAdapter.ViewHolder>() {
    lateinit var iMainClaim: IMainClaim
    init {
        val context = context;
        if(context is IMainClaim){
            iMainClaim = context as IMainClaim
        }else{
            throw ClassCastException()
        }
    }

    inner class  ViewHolder(listItemView: View, context: Context) : RecyclerView.ViewHolder(listItemView){
        val mc = itemView.findViewById<TextView>(R.id.txt_ChooseMC_item)
        val tick = itemView.findViewById<ImageView>(R.id.img_ChooseMC_Tick)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val chooseMCView = inflater.inflate(R.layout.instructor_gameroom_item_mc, parent, false)

        return ViewHolder(chooseMCView, context)
    }

    override fun getItemCount(): Int {
        return mMCs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mcList: ArrayList<MainClaim> = ArrayList(mMCs.keys)
        val valueList: ArrayList<Boolean> = ArrayList(mMCs.values)
        val mainClaim: MainClaim = mcList[position]

        holder.tick.isVisible = valueList[position];
        holder.mc.text = mainClaim.statement

        holder.itemView.setOnClickListener(View.OnClickListener {
//            //save mainClaimId into sharedPreferences
//            val sharedPref: SharedPreferences = holder.itemView.context.getSharedPreferences(R.string.sharedPref.toString(), Context.MODE_PRIVATE)
//            val editor: SharedPreferences.Editor = sharedPref.edit()
//            editor.putInt(R.string.mainClaimId.toString(), mainClaimList.mainClaimId)
//            editor.apply()

            val newStatus = !valueList[position]
            iMainClaim.updateMainClaimStatus(mainClaim, newStatus)
            holder.tick.isVisible = newStatus;

                Log.i("MainClaim Status: ", mainClaim.statement + " " + newStatus)


//            //Go to setTime activity
//            val setTimeIntent = Intent(holder.itemView.context, InstructorSetTime::class.java)
//            holder.itemView.context.startActivity(setTimeIntent)
        })
    }


}