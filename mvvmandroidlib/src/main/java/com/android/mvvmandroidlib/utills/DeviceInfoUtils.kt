package com.android.mvvmandroidlib.utills

import android.os.Build

object DeviceInfoUtils {

    private val TAG: String = DeviceInfoUtils::class.java.simpleName

    /**
     * @return Unique Device ID
     */
    fun getDeviceID(): String {
        LoggerUtils.info(TAG, "Device ID: " + Build.ID)
        return Build.ID
    }

    /**
     * @return Device Name
     */
    fun getDeviceName(): String {
        LoggerUtils.info(TAG, "Device Name: " + Build.PRODUCT)
        return Build.PRODUCT
    }

    /**
     * @return Device Model
     */
    fun getDeviceModel(): String {
        LoggerUtils.info(TAG, "Device MODEL: " + Build.MODEL)
        return Build.MODEL
    }

    /**
     * @return Device Brand Name
     */
    fun getDeviceBrandName(): String {
        LoggerUtils.info(TAG, "Device BRAND: " + Build.BRAND)
        return Build.BRAND
    }

    /**
     * @return Device Type
     */
    fun getDeviceType(): String {
        LoggerUtils.info(TAG, "Device TYPE: " + Build.TYPE)
        return Build.TYPE
    }

    /**
     * @return Device Hardware Information
     */
    fun getDeviceHardware(): String {
        LoggerUtils.info(TAG, "Device HARDWARE: " + Build.HARDWARE)
        return Build.HARDWARE
    }

    /**
     * @return Device Manufacturer Name
     */
    fun getDeviceManufacturerName(): String {
        LoggerUtils.info(TAG, "Device MANUFACTURER: " + Build.MANUFACTURER)
        return Build.MANUFACTURER
    }

    /**
     * @return Device Android Version
     */
    fun getAndroidOSVersion(): String {
        LoggerUtils.info(TAG, "Device Android Version: " + Build.VERSION.RELEASE)
        return Build.VERSION.RELEASE
    }

    /**
     * @return Device Android Version Code
     */
    fun getAndroidOSVersionCode(): Int {
        LoggerUtils.info(TAG, "Device Android VersionCode: " + Build.VERSION.SDK_INT)
        return Build.VERSION.SDK_INT
    }

    /**
     * @return Device Fingerprint
     */
    fun getDeviceFingerprint(): String {
        LoggerUtils.info(TAG, "Device Fingerprint: " + Build.FINGERPRINT)
        return Build.FINGERPRINT
    }
}