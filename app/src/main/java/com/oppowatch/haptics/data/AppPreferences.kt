package com.oppowatch.haptics.data

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("haptic_master_prefs", Context.MODE_PRIVATE)

    var isFeelTheWearEnabled: Boolean
        get() = prefs.getBoolean("feel_the_wear_enabled", true)
        set(value) = prefs.edit().putBoolean("feel_the_wear_enabled", value).apply()

    var selectedPattern: Int
        get() = prefs.getInt("selected_pattern", 1)
        set(value) = prefs.edit().putInt("selected_pattern", value).apply()
}