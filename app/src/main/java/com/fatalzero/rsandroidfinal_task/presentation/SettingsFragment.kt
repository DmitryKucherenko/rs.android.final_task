package com.fatalzero.rsandroidfinal_task.presentation

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.fatalzero.rsandroidfinal_task.R
import com.fatalzero.rsandroidfinal_task.utils.ThemeManager


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val themePreference = findPreference<SwitchPreference>("switch")
        themePreference?.setOnPreferenceChangeListener { _, _ ->
            ThemeManager.reverseTheme()
         //   requireActivity().recreate()
            true
        }
    }

}



