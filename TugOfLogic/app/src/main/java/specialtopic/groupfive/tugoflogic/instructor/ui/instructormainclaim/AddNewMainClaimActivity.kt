package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.roomdb.DataRepository
import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import kotlin.random.Random

class AddNewMainClaimActivity : AppCompatActivity() {

    private lateinit var tugDataRepo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_main_claim)

        // Init data repository for using on this fragment
        tugDataRepo = application?.let { DataRepository(it) }!!

        // Test dummy data
        val newMainClaim = MainClaim(Random.nextInt(100000, 1000000), 1, "Hello from Chau", 0, 0)
        tugDataRepo.addNewMainClaim(this.application, newMainClaim)
    }
}