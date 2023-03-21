package com.example.enhanceittechincaltest

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.enhanceittechincaltest.base.ActivityBase
import com.example.enhanceittechincaltest.databinding.ActMainLBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ActivityBase() {

    private lateinit var binding: ActMainLBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActMainLBinding.inflate(layoutInflater)
        setContentView(binding.root)
        doAfterSetContentView(savedInstanceState)
    }

    override fun setUpActivity(savedInstanceState: Bundle?) {
        super.setUpActivity(savedInstanceState)
        setUpBottomNavBar()
    }

    private fun setUpBottomNavBar() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}