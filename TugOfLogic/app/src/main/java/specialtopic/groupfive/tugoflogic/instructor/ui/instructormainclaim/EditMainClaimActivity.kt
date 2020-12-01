package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_new_main_claim.*
import kotlinx.android.synthetic.main.activity_edit_main_claim.*
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

class EditMainClaimActivity : AppCompatActivity() {
    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_main_claim)

        // Init data repository for using on this activity
        tugDataRepo = DataRepository(application)
        val mainClaimId: Int = intent.getIntExtra("mainClaimId", 0)

        // Button OnClick: Update Main Claim
        btnUpdateMainClaim.setOnClickListener(View.OnClickListener {
            val inputStatement = editTextUpdateNewMainClaim.text.toString().trim()
            if (inputStatement == "") {
                Toast.makeText(this, "Please enter a Main Claim statement!", Toast.LENGTH_LONG)
                    .show()
                return@OnClickListener
            }

            val updatedMC = MainClaim(mainClaimId, inputStatement)
            tugDataRepo.updateMainClaim(mainClaimId, updatedMC)
            finish()
        })

        // Button OnClick: Cancel Update Main Claim
        btnCancelUpdateMainClaim.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}