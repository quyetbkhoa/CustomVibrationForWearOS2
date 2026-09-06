# Haptic Master - Wear OS 2 Custom Vibration Controller

[![GitHub Release](https://img.shields.io/github/v/release/quyetbkhoa/CustomVibrationForWearOS2?style=flat-square&color=blue)](https://github.com/quyetbkhoa/CustomVibrationForWearOS2/releases/latest)
[![Download APK](https://img.shields.io/badge/Download-HapticMaster--v1.0.0.apk-brightgreen?style=flat-square&logo=android)](https://github.com/quyetbkhoa/CustomVibrationForWearOS2/releases/download/v1.0.0/HapticMaster-v1.0.0.apk)

Ứng dụng tùy chỉnh độ rung xúc giác (Haptic Feedback) và mẫu rung thông báo đa dạng dành riêng cho các thiết bị chạy **Wear OS 2** (Android 8.0/9.0 Ore/Pie), tối ưu đặc biệt cho **OPPO Watch 46mm / 41mm**.

---

## 🌟 Tính năng nổi bật

1. **Bộ điều khiển cường độ rung hệ thống (System Haptic Intensity)**:
   - Điều khiển trực tiếp các tham số rung sâu trong hệ điều hành (HAPTIC_FEEDBACK_INTENSITY, NOTIFICATION_VIBRATION_INTENSITY, v.v.).
   - 3 cấp độ tinh chỉnh: Nhẹ (Subtle), Vừa (Medium), Mạnh (Ultra/Maximum).

2. **Custom Notification Vibration Patterns (Thay thế Feel The Wear)**:
   - Sử dụng NotificationListenerService can thiệp và kích hoạt các kiểu rung đặc trưng mỗi khi có thông báo đến.
   - Hỗ trợ các kiểu rung được thiết kế riêng:
     - **Default Heartbeat**: Nhịp đập 2 nấc cổ điển.
     - **Buzz Sharp**: Rung dứt khoát, thích hợp cho tin nhắn khẩn cấp.
     - **Long Pulse**: Xung rung dài không bỏ sót cuộc gọi/thông báo quan trọng.
     - **SOS Pattern**: Rung theo mã Morse SOS.
     - **Staccato / Triple Kick**: 3 nhịp gõ đanh thép.

3. **Giao diện Tactile Dark Glassmorphism tối ưu cho màn hình AMOLED Watch**:
   - Nền đen tuyệt đối tiết kiệm pin tối đa.
   - Các nút bấm mô phỏng cơ học với hiệu ứng phản hồi rung tức thì khi chạm.

4. **Tương thích Wear OS 2 (API 26 - 28)**:
   - Không bị giới hạn bởi các API của Wear OS 3/4.
   - Sử dụng VibrationEffect và backward compatibility cho Vibrator truyền thống.

---

## 🚀 Cài đặt & Cấp quyền qua ADB

Do ứng dụng can thiệp vào cài đặt hệ thống và lắng nghe thông báo trên Wear OS 2, bạn cần cấp các quyền sau thông qua ADB:

`ash
# 1. Cài đặt file APK
adb install -r CustomHapticApp.apk

# 2. Cấp quyền ghi cài đặt hệ thống bảo mật (WRITE_SECURE_SETTINGS)
adb shell pm grant com.oppowatch.haptics android.permission.WRITE_SECURE_SETTINGS

# 3. Kích hoạt dịch vụ lắng nghe thông báo (Notification Listener)
adb shell cmd notification allow_listener com.oppowatch.haptics/.service.HapticNotificationListener

# 4. Đưa vào danh sách loại trừ tiết kiệm pin (Doze Whitelist) để không bị ngắt ngầm
adb shell dumpsys deviceidle whitelist +com.oppowatch.haptics
`

> Hoặc bạn có thể chạy file script tự động CAI_DAT_HAPTIC_MASTER.bat.

---

## 🛠️ Build từ mã nguồn

Dự án sử dụng **Gradle** và **Android Gradle Plugin (AGP)**:

`ash
# Build Debug APK
./gradlew assembleDebug

# Build Release APK
./gradlew assembleRelease
`

File APK sau khi build nằm tại:
pp/build/outputs/apk/debug/app-debug.apk

---

## 📱 Cấu trúc thư mục

`
CustomHapticApp/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/oppowatch/haptics/
│   │   │   ├── MainActivity.kt               # Giao diện chính & trình điều khiển
│   │   │   ├── data/AppPreferences.kt         # Lưu trạng thái cài đặt
│   │   │   ├── engine/
│   │   │   │   ├── SystemIntensityManager.kt  # Quản lý cường độ rung hệ thống
│   │   │   │   └── VibrationPatternEngine.kt  # Xử lý các mẫu sóng rung
│   │   │   └── service/
│   │   │       └── HapticNotificationListener.kt # Lắng nghe thông báo để rung
│   │   └── res/                              # Layout, drawable, styles
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
`

---

## 📜 License
MIT License
