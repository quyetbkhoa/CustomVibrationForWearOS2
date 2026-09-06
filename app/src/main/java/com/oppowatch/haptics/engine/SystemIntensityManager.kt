package com.oppowatch.haptics.engine

import android.content.Context
import android.provider.Settings
import android.util.Log

object SystemIntensityManager {

    private const val TAG = "SystemIntensityManager"

    fun getNotificationIntensity(context: Context): Int {
        return try {
            Settings.System.getInt(
                context.contentResolver,
                "notification_vibration_intensity",
                2
            )
        } catch (e: Exception) {
            2
        }
    }

    fun setIntensity(context: Context, level: Int): Boolean {
        return try {
            val safeLevel = level.coerceIn(1, 3)
            val r1 = Settings.System.putInt(
                context.contentResolver,
                "notification_vibration_intensity",
                safeLevel
            )
            val r2 = Settings.System.putInt(
                context.contentResolver,
                "haptic_feedback_intensity",
                safeLevel
            )
            r1 && r2
        } catch (e: SecurityException) {
            Log.e(TAG, "WRITE_SECURE_SETTINGS not granted: ${e.message}")
            false
        } catch (e: Exception) {
            Log.e(TAG, "Error setting intensity: ${e.message}")
            false
        }
    }
}