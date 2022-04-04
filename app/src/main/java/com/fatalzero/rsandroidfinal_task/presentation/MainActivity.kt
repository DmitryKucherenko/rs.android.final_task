package com.fatalzero.rsandroidfinal_task.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.utils.ThemeManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val NIGHT_THEME = true
private const val DAY_THEME = false

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    val mainViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = this.findNavController(R.id.fragment_container)

        mainViewModel.loadSettings()
        lifecycleScope.launchWhenStarted {
            mainViewModel.isDarkTheme.collectLatest {
                when (it) {
                    NIGHT_THEME -> ThemeManager.setNightTheme()
                    DAY_THEME -> ThemeManager.setDayTheme()
                }
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)
    }


}
