@echo off
title Cai dat & Cap quyen Haptic Master - Wear OS 2
echo Dang kiem tra ket noi ADB...
adb devices
echo.
echo 1. Dang cai dat file APK...
if exist "%~dp0app\build\outputs\apk\debug\app-debug.apk" (
    adb install -r "%~dp0app\build\outputs\apk\debug\app-debug.apk"
) else (
    echo Khong tim thay file APK debug. Vui long build project truoc hoac copy file APK vao!
)
echo.
echo 2. Dang cap quyen WRITE_SECURE_SETTINGS...
adb shell pm grant com.oppowatch.haptics android.permission.WRITE_SECURE_SETTINGS
echo.
echo 3. Dang cap quyen Notification Listener (Feel The Wear)...
adb shell cmd notification allow_listener com.oppowatch.haptics/.service.HapticNotificationListener
echo.
echo 4. Dang them vao danh sach khong tat tiet kiem pin (Doze whitelist)...
adb shell dumpsys deviceidle whitelist +com.oppowatch.haptics
echo.
echo ==========================================================
echo   HOAN TAT! DA CAP DAY DU TAT CA QUYEN CHO HAPTIC MASTER!
echo ==========================================================
pause
