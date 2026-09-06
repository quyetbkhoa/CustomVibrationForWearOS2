package com.oppowatch.haptics.engine

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object VibrationPatternEngine {

    const val PATTERN_LONG = 0
    const val PATTERN_DOUBLE = 1
    const val PATTERN_STACCATO = 2
    const val PATTERN_HEARTBEAT = 3

    fun getPatternTimings(patternType: Int): LongArray {
        return when (patternType) {
            PATTERN_LONG -> longArrayOf(0, 1200)
            PATTERN_DOUBLE -> longArrayOf(0, 350, 150, 700)
            PATTERN_STACCATO -> longArrayOf(0, 150, 70, 150, 70, 150, 70, 400)
            PATTERN_HEARTBEAT -> longArrayOf(0, 120, 100, 300, 500, 120, 100, 300)
            else -> longArrayOf(0, 500)
        }
    }

    /**
     * Preview specifically for Level 1, Level 2, Level 3
     * Level 1: Soft gentle pulse (low amplitude 80, 150ms)
     * Level 2: Medium solid pulse (medium amplitude 170, 400ms)
     * Level 3: Heavy max punch (full amplitude 255, 800ms)
     */
    fun vibrateIntensityPreview(context: Context, level: Int) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator ?: return
        if (!vibrator.hasVibrator()) return

        val (duration, amplitude) = when (level) {
            1 -> 150L to 80
            2 -> 400L to 170
            3 -> 800L to 255
            else -> 400L to 170
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val effect = VibrationEffect.createOneShot(duration, amplitude)
                vibrator.vibrate(effect)
            } catch (e: Exception) {
                @Suppress("DEPRECATION")
                vibrator.vibrate(duration)
            }
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(duration)
        }
    }

    fun vibrate(context: Context, patternType: Int, intensityLevel: Int = 3) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator ?: return
        if (!vibrator.hasVibrator()) return

        val timings = getPatternTimings(patternType)
        val maxAmp = when (intensityLevel) {
            1 -> 90
            2 -> 180
            3 -> 255
            else -> 255
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val amplitudes = IntArray(timings.size) { index ->
                if (index % 2 == 0) 0 else maxAmp
            }
            try {
                val effect = VibrationEffect.createWaveform(timings, amplitudes, -1)
                vibrator.vibrate(effect)
            } catch (e: Exception) {
                val fallbackEffect = VibrationEffect.createWaveform(timings, -1)
                vibrator.vibrate(fallbackEffect)
            }
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(timings, -1)
        }
    }
}