package specialtopic.groupfive.tugoflogic.student

import android.content.Context
import android.os.Bundle
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
import specialtopic.groupfive.tugoflogic.utilities.NetworkHelper

class StudentMainActivity : AppCompatActivity() {

    lateinit var actContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_gameboard, R.id.navigation_home, R.id.navigation_reasons
            )
        )

        NetworkHelper.mSocket.on("newRipFromPlayer", onNewRipComing)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private val onNewRipComing = Emitter.Listener {
        runOnUiThread {
            Toast.makeText(applicationContext, "User update reason in play", Toast.LENGTH_SHORT)
                .show()
        }
    }
}