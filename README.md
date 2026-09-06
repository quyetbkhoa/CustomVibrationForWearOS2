# Haptic Master - Wear OS 2

[![GitHub Release](https://img.shields.io/github/v/release/quyetbkhoa/CustomVibrationForWearOS2?style=flat-square&color=blue)](https://github.com/quyetbkhoa/CustomVibrationForWearOS2/releases/latest)
[![Download APK](https://img.shields.io/badge/Download-HapticMaster--v1.0.0.apk-brightgreen?style=flat-square&logo=android)](https://github.com/quyetbkhoa/CustomVibrationForWearOS2/releases/download/v1.0.0/HapticMaster-v1.0.0.apk)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](LICENSE)

[English](#english) | [Tiếng Việt](#tiếng-việt)

---

## English

A custom vibration and haptic feedback controller designed for **Wear OS 2** (Android 8.0/9.0), optimized for **OPPO Watch 46mm / 41mm**. Serves as a modern replacement for deprecated tools like *Feel The Wear*.

### ✨ Features
- **System Intensity Tuning**: Adjust OS-level haptics (Subtle / Medium / Ultra).
- **Custom Patterns**: Distinct vibration patterns for notifications (Heartbeat, Buzz Sharp, Long Pulse, SOS, Triple Kick).
- **AMOLED-Optimized UI**: Pure black tactile UI with instant haptic preview.
- **Background Persistence**: Runs reliably via NotificationListenerService and battery whitelist.

### ⚡ Quick Setup (ADB)
`ash
# 1. Install APK
adb install -r HapticMaster-v1.0.0.apk

# 2. Grant system settings permission
adb shell pm grant com.oppowatch.haptics android.permission.WRITE_SECURE_SETTINGS

# 3. Enable Notification Listener
adb shell cmd notification allow_listener com.oppowatch.haptics/.service.HapticNotificationListener

# 4. Whitelist from battery optimization
adb shell dumpsys deviceidle whitelist +com.oppowatch.haptics
`
*(Windows users can also run install_and_grant_permissions.bat)*

### 🛠️ Build
`ash
./gradlew assembleDebug
`

---

## Tiếng Việt

Ứng dụng tùy chỉnh độ rung xúc giác và mẫu rung thông báo dành cho **Wear OS 2** (Android 8.0/9.0), tối ưu đặc biệt cho **OPPO Watch 46mm / 41mm**. Giải pháp thay thế hoàn hảo cho *Feel The Wear*.

### ✨ Tính năng
- **Chỉnh cường độ hệ thống**: Can thiệp độ rung toàn hệ thống (Nhẹ / Vừa / Cực mạnh).
- **Mẫu rung đa dạng**: Tùy chọn kiểu rung khi có thông báo (Heartbeat, Buzz Sharp, Long Pulse, SOS, Triple Kick).
- **Giao diện tối ưu AMOLED**: Nền đen tiết kiệm pin kèm phản hồi rung tức thì khi bấm.
- **Chạy nền bền bỉ**: Sử dụng NotificationListenerService và loại trừ tối ưu hóa pin.

### ⚡ Cài đặt nhanh (ADB)
`ash
# 1. Cài đặt APK
adb install -r HapticMaster-v1.0.0.apk

# 2. Cấp quyền cài đặt hệ thống
adb shell pm grant com.oppowatch.haptics android.permission.WRITE_SECURE_SETTINGS

# 3. Kích hoạt lắng nghe thông báo
adb shell cmd notification allow_listener com.oppowatch.haptics/.service.HapticNotificationListener

# 4. Bỏ qua chế độ tiết kiệm pin (Doze)
adb shell dumpsys deviceidle whitelist +com.oppowatch.haptics
`
*(Người dùng Windows có thể chạy trực tiếp file install_and_grant_permissions.bat)*

### 🛠️ Build mã nguồn
`ash
./gradlew assembleDebug
`

---

## License
MIT License © 2026
