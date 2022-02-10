package com.fatalzero.rsandroidfinal_task.utils

import androidx.appcompat.app.AppCompatDelegate

class ThemeManager {
    companion object {
        fun reverseTheme() {
            if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        fun getCurrentTheme():Int{
            return AppCompatDelegate.getDefaultNightMode()
        }

        fun setDayTheme(){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        fun setNightTheme(){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
