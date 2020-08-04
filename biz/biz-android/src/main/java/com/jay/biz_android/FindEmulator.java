package com.jay.biz_android;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.kejiee.huaxindou.ndk.CPUFrameworkHelper;

/**
 * 检查当前设备是否为模拟器
 */
public class FindEmulator {

    public static boolean isEmulator(Context context) {
        boolean notHasBlueTooth = FindEmulator.notHasBlueTooth();
        boolean isFeatures = FindEmulator.isFeatures();
        boolean isX86 = FindEmulator.isX86();
        Log.i("notHasBlueTooth=====", String.valueOf(notHasBlueTooth));
        Log.i("isFeatures=====", String.valueOf(isFeatures));
        Log.i("isX86=====", String.valueOf(isX86));
        if (notHasBlueTooth || isFeatures || isX86) {
            Log.i("isEmulator=========", "true");
            return true;
        }
        Log.i("isEmulator=========", "false");
        return false;
    }

    /**
     * 判断蓝牙是否有效来判断是否为模拟器
     *
     * @return true 为模拟器
     */
    public static boolean notHasBlueTooth() {
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if (ba == null) {
            return true;
        } else {
            // 如果有蓝牙不一定是有效的。获取蓝牙名称，若为null 则默认为模拟器
            String name = ba.getName();
            return TextUtils.isEmpty(name);
        }
    }

    /**
     * 根据部分特征参数设备信息来判断是否为模拟器
     *
     * @return true 为模拟器
     */
    public static boolean isFeatures() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.toLowerCase().contains("vbox")
                || Build.FINGERPRINT.toLowerCase().contains("test-keys")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    /**
     * 判断cpu架构是否是x86，x86架构认为是模拟器
     *
     * @return
     */
    public static boolean isX86() {
        return CPUFrameworkHelper.isX86Cpu();
    }
}

