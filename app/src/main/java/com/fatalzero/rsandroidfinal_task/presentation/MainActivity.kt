package com.fatalzero.rsandroidfinal_task.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fatalzero.rsandroidfinal_task.App
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.ThemeManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

private const val NIGHT_THEME = true
private const val DAY_THEME = false

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private val component by lazy {
        (application as App).appComponent
    }

    @Inject
    lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
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
