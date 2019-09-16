package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

/**
 * This util class is used to provide some methods to know display information.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object DisplayUtils {

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    /**
     * Method is used to get screen width in pixel.
     *
     * @param context context
     * @return screen width in pixel.
     */
    fun getScreenWidth(context: Activity): Int {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
     * Method is used to get screen height in pixel.
     *
     * @param context context
     * @return screen height in pixel.
     */
    fun getScreenHeight(context: Activity): Int {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    /**
     * Method is used to get NavigationBar height in pixel.
     *
     * @param context context
     * @return Navigation Bar height in pixel.
     */
    fun getNavigationBarHeight(context: Activity): Int {
        val metrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metrics)
        val usableHeight = metrics.heightPixels
        context.windowManager.defaultDisplay.getRealMetrics(metrics)
        val realHeight = metrics.heightPixels
        return if (realHeight > usableHeight)
            realHeight - usableHeight
        else
            0
    }

    /**
     * Method is used to get screen width in dpi.
     *
     * @param context context
     * @return screen width in dpi.
     */
    fun getXDpi(context: Activity): Float {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.xdpi
    }

    /**
     * Method is used to get screen height in dpi.
     *
     * @param context context
     * @return screen height in dpi.
     */
    fun getYDpi(context: Activity): Float {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.ydpi
    }

    /**
     * Method is used to get screen density in dpi.
     *
     * @param context context
     * @return screen density in dpi.
     */
    fun getDensityDpi(context: Activity): Int {
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.densityDpi
    }
}