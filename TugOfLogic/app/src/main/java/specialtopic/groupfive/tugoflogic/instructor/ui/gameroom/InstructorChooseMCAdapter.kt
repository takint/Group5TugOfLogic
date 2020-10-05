package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import specialtopic.groupfive.tugoflogic.R

class InstructorChooseMCAdapter(private val mMCs: ArrayList<String>): RecyclerView.Adapter<InstructorChooseMCAdapter.ViewHolder>() {

    inner class  ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val mc = itemView.findViewById<TextView>(R.id.txt_ChooseMC_item)
        //val tick = itemView.findViewById<ImageView>(R.id.img_ChooseMC_Tick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val chooseMCView = inflater.inflate(R.layout.instructor_gameroom_item_mc, parent, false)

        return ViewHolder(chooseMCView)
    }

    override fun getItemCount(): Int {
        return mMCs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainClaim: String = mMCs.get(position)

        val textView = holder.mc
        textView.setText(mainClaim)
    }
}