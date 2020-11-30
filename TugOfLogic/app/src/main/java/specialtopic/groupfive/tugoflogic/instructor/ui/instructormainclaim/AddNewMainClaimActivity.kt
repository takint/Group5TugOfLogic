package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_new_main_claim.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import kotlin.random.Random

class AddNewMainClaimActivity : AppCompatActivity() {

    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_main_claim)

        // Init data repository for using on this activity
        tugDataRepo = application?.let { DataRepository(it) }!!

        // Button OnClick: Add New Main Claim
        btnAddNewMainClaim.setOnClickListener(View.OnClickListener {
            val randomMainClaimID = Random.nextInt(100000, 1000000)
            val inputStatement: String = editTextAddNewMainClaim.text.toString().trim()

            if (inputStatement == "") {
                Toast.makeText(this, "Please enter a Main Claim statement!", Toast.LENGTH_LONG)
                    .show()
                return@OnClickListener
            }

            val newMainClaim = MainClaim(randomMainClaimID, inputStatement)
            tugDataRepo.addNewMainClaim(this.application, newMainClaim)
            Toast.makeText(this, "A new Main Claim is added!", Toast.LENGTH_LONG).show()
        })

        // Button OnClick: Cancel New Main Claim
        btnCancelNewMainClaim.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}