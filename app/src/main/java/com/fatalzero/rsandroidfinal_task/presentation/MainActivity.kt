package com.fatalzero.rsandroidfinal_task.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.presentation.viewModel.MainActivityViewModel
import com.fatalzero.rsandroidfinal_task.utils.ThemeManager
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null
    val mainViewModel: MainActivityViewModel by viewModels {
        MainActivityViewModel.MainActivityViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = this.findNavController(R.id.fragment_container)
        mainViewModel.loadSettings()
        lifecycleScope.launchWhenStarted {
            mainViewModel.isDarkTheme.collectLatest {
                if (it) {
                    ThemeManager.setNightTheme()
                } else {
                    ThemeManager.setDayTheme()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.jokes_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_theme -> {

                navController?.navigate(R.id.settingsFragment)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
