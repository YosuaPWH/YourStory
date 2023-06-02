package com.yosua.yourstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yosua.yourstory.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(binding.mainFragmentContainer.id) as NavHostFragment

        val inflater = navHost.navController.navInflater
        val graph = inflater.inflate(R.navigation.app_navigation)

        lifecycleScope.launch {
            viewModel.getUser().collect {
                if (it != null) {
                    graph.setStartDestination(R.id.navigation_home)
                    navHost.navController.graph = graph
                } else {
                    graph.setStartDestination(R.id.startedFragment)
                    navHost.navController.graph = graph
                }
            }
        }

        binding.bottomNav.setupWithNavController(navHost.navController)
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            Handler(Looper.getMainLooper()).post {
                binding.bottomNav.visibility = when (destination.id) {
                    R.id.navigation_home -> View.VISIBLE
                    R.id.navigation_account -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }
}