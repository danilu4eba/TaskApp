package com.example.taskapp
import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp.data.local.Pref
import com.example.taskapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        if(!pref.isUserSeen())
        navController.navigate(R.id.onBoardingFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_notifications, R.id.taskFragment, R.id.navigation_profile
            )
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.onBoardingFragment) {
                navView.isVisible = false
                supportActionBar?.hide()
            } else {
                navView.isVisible = true
                supportActionBar?.show()
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}