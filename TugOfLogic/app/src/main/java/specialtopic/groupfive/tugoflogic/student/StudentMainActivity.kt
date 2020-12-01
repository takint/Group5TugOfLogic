package specialtopic.groupfive.tugoflogic.student

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.UiThread
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.nkzawa.emitter.Emitter
import specialtopic.groupfive.tugoflogic.R
import specialtopic.groupfive.tugoflogic.utilities.*

class StudentMainActivity : AppCompatActivity() {

    lateinit var actContext: Context
    lateinit var currentMainClaim: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentMainClaim = bundle.getString(MAIN_CLAIM).toString()
        }

        Log.i("SELECTED MAIN CLAIM: ", currentMainClaim)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_gameboard, R.id.navigation_home, R.id.navigation_reasons
            )
        )

        NetworkHelper.mSocket.on("newRipFromPlayer", onNewRipComing)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        NetworkHelper.mSocket.on("notification_current_mainclaim", onCurrentMainClaim)
    }

    private val onNewRipComing = Emitter.Listener {
        runOnUiThread {
            Toast.makeText(applicationContext, "User update reason in play", Toast.LENGTH_SHORT)
                .show()
        }
    }

    var onCurrentMainClaim = Emitter.Listener {
        var message = it[0] as String
        Log.i("Current MainCLaim: ", message)
    }
}