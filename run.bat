adb push .\app\build\outputs\apk\app-debug.apk /data/local/tmp
adb forward tcp:52174 tcp:52174
adb shell "export CLASSPATH=/data/local/tmp/app-debug.apk;exec app_process /system/bin com.mzj.vysor.Main"
