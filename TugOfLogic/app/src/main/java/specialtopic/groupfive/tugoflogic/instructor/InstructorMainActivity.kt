package specialtopic.groupfive.tugoflogic.instructor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import specialtopic.groupfive.tugoflogic.R

class InstructorMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_main)

        // Navigation bar
        val navView: BottomNavigationView = findViewById(R.id.nav_view_instructor)
        val navController = findNavController(R.id.nav_host_fragment_instructor)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_instructor_game,
                R.id.navigation_instructor_mainclaim,
                R.id.navigation_instructor_statistics
            )
        )

        // Notice: I don't need the toolbar for now - Oct 3rd, 2020 - Chau
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }
}