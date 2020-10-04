package specialtopic.groupfive.tugoflogic.instructor.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import specialtopic.groupfive.tugoflogic.R

class InstructorMainClaimsListAdapter(
    private val context: Activity,
    private val ids: Array<Int>,
    private val contents: Array<String>
) : ArrayAdapter<String>(context, R.layout.layout_instructor_mainclaim_item, contents) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        // Check if an existing view is being reused, otherwise inflate the view
        val inflater = context.layoutInflater
        val itemView = inflater.inflate(R.layout.layout_instructor_mainclaim_item, parent, false)

        val txtMainClaimId = itemView.findViewById(R.id.textView_main_claim_id) as TextView
        val txtMainClaimContent =
            itemView.findViewById(R.id.textView_main_claim_content) as TextView
        val btnEdit = itemView.findViewById(R.id.btn_main_claim_edit) as ImageButton
        val btnDelete = itemView.findViewById(R.id.btn_main_claim_delete) as ImageButton

        txtMainClaimId.text = ids[position].toString()
        txtMainClaimContent.text = contents[position]

        return itemView
    }
}