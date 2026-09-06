package com.oppowatch.haptics.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.oppowatch.haptics.data.AppPreferences
import com.oppowatch.haptics.engine.VibrationPatternEngine

class HapticNotificationListener : NotificationListenerService() {

    private val prefs by lazy { AppPreferences(this) }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (sbn == null) return

        if (!prefs.isFeelTheWearEnabled) return
        if (sbn.packageName == packageName) return

        val notification = sbn.notification ?: return

        val isOngoing = (notification.flags and Notification.FLAG_ONGOING_EVENT) != 0
        val isForegroundService = (notification.flags and Notification.FLAG_FOREGROUND_SERVICE) != 0
        if (isOngoing || isForegroundService) return

        val pattern = prefs.selectedPattern
        Log.d("HapticListener", "Notification from ${sbn.packageName}, firing pattern: $pattern")
        VibrationPatternEngine.vibrate(this, pattern)
    }
}