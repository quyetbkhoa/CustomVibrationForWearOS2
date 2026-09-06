package com.oppowatch.haptics

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.oppowatch.haptics.data.AppPreferences
import com.oppowatch.haptics.engine.SystemIntensityManager
import com.oppowatch.haptics.engine.VibrationPatternEngine

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: AppPreferences

    private lateinit var btnLevel1: Button
    private lateinit var btnLevel2: Button
    private lateinit var btnLevel3: Button
    private lateinit var tvIntensityStatus: TextView

    private lateinit var btnEngineToggle: Button
    private lateinit var btnPatLong: Button
    private lateinit var btnPatDouble: Button
    private lateinit var btnPatStaccato: Button
    private lateinit var btnPatHeartbeat: Button

    private lateinit var btnTestVibration: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = AppPreferences(this)

        initViews()
        loadState()
        setupListeners()
    }

    private fun initViews() {
        btnLevel1 = findViewById(R.id.btn_level_1)
        btnLevel2 = findViewById(R.id.btn_level_2)
        btnLevel3 = findViewById(R.id.btn_level_3)
        tvIntensityStatus = findViewById(R.id.tv_intensity_status)

        btnEngineToggle = findViewById(R.id.btn_engine_toggle)
        btnPatLong = findViewById(R.id.btn_pat_long)
        btnPatDouble = findViewById(R.id.btn_pat_double)
        btnPatStaccato = findViewById(R.id.btn_pat_staccato)
        btnPatHeartbeat = findViewById(R.id.btn_pat_heartbeat)

        btnTestVibration = findViewById(R.id.btn_test_vibration)
    }

    private fun loadState() {
        val currentLevel = SystemIntensityManager.getNotificationIntensity(this)
        updateIntensityUi(currentLevel)

        updateEngineUi(prefs.isFeelTheWearEnabled)
        updatePatternUi(prefs.selectedPattern)
    }

    private fun setupListeners() {
        btnLevel1.setOnClickListener { applyIntensity(1) }
        btnLevel2.setOnClickListener { applyIntensity(2) }
        btnLevel3.setOnClickListener { applyIntensity(3) }

        btnEngineToggle.setOnClickListener {
            val newState = !prefs.isFeelTheWearEnabled
            prefs.isFeelTheWearEnabled = newState
            updateEngineUi(newState)
            VibrationPatternEngine.vibrateIntensityPreview(this, 2)
        }

        btnPatLong.setOnClickListener { selectPattern(VibrationPatternEngine.PATTERN_LONG) }
        btnPatDouble.setOnClickListener { selectPattern(VibrationPatternEngine.PATTERN_DOUBLE) }
        btnPatStaccato.setOnClickListener { selectPattern(VibrationPatternEngine.PATTERN_STACCATO) }
        btnPatHeartbeat.setOnClickListener { selectPattern(VibrationPatternEngine.PATTERN_HEARTBEAT) }

        btnTestVibration.setOnClickListener {
            val currentLevel = SystemIntensityManager.getNotificationIntensity(this)
            VibrationPatternEngine.vibrate(this, prefs.selectedPattern, currentLevel)
        }
    }

    private fun applyIntensity(level: Int) {
        val success = SystemIntensityManager.setIntensity(this, level)
        updateIntensityUi(level)
        if (!success) {
            tvIntensityStatus.text = "Level: $level (Requires ADB Grant)"
            tvIntensityStatus.setTextColor(Color.parseColor("#FFB300"))
        }
        // Play DISTINCT tactile feedback matching the chosen level!
        VibrationPatternEngine.vibrateIntensityPreview(this, level)
    }

    private fun updateIntensityUi(activeLevel: Int) {
        btnLevel1.isSelected = (activeLevel == 1)
        btnLevel2.isSelected = (activeLevel == 2)
        btnLevel3.isSelected = (activeLevel == 3)

        btnLevel1.setTextColor(if (activeLevel == 1) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))
        btnLevel2.setTextColor(if (activeLevel == 2) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))
        btnLevel3.setTextColor(if (activeLevel == 3) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))

        val label = when (activeLevel) {
            1 -> "1: SOFT (Low Power)"
            2 -> "2: MEDIUM (Balanced)"
            3 -> "3: MAXIMUM (High Power)"
            else -> "$activeLevel"
        }
        tvIntensityStatus.text = "Active Level: $label"
        tvIntensityStatus.setTextColor(Color.parseColor("#00E676"))
    }

    private fun updateEngineUi(enabled: Boolean) {
        if (enabled) {
            btnEngineToggle.text = "ACTIVE"
            btnEngineToggle.setTextColor(Color.parseColor("#00E676"))
        } else {
            btnEngineToggle.text = "OFF"
            btnEngineToggle.setTextColor(Color.parseColor("#FF1744"))
        }
    }

    private fun selectPattern(pattern: Int) {
        prefs.selectedPattern = pattern
        updatePatternUi(pattern)
        val currentLevel = SystemIntensityManager.getNotificationIntensity(this)
        VibrationPatternEngine.vibrate(this, pattern, currentLevel)
    }

    private fun updatePatternUi(pattern: Int) {
        btnPatLong.isSelected = (pattern == VibrationPatternEngine.PATTERN_LONG)
        btnPatDouble.isSelected = (pattern == VibrationPatternEngine.PATTERN_DOUBLE)
        btnPatStaccato.isSelected = (pattern == VibrationPatternEngine.PATTERN_STACCATO)
        btnPatHeartbeat.isSelected = (pattern == VibrationPatternEngine.PATTERN_HEARTBEAT)

        btnPatLong.setTextColor(if (pattern == VibrationPatternEngine.PATTERN_LONG) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))
        btnPatDouble.setTextColor(if (pattern == VibrationPatternEngine.PATTERN_DOUBLE) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))
        btnPatStaccato.setTextColor(if (pattern == VibrationPatternEngine.PATTERN_STACCATO) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))
        btnPatHeartbeat.setTextColor(if (pattern == VibrationPatternEngine.PATTERN_HEARTBEAT) Color.parseColor("#FFB300") else Color.parseColor("#E2E8F0"))
    }
}