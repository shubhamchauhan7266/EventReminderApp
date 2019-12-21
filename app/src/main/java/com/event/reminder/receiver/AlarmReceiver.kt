package com.event.reminder.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import com.android.mvvmandroidlib.utills.LoggerUtils
import java.io.IOException

/**
 * This receiver class is used to ring alarm for any event.
 *
 * @author Shubham Chauhan
 */
class AlarmReceiver : BroadcastReceiver() {
    private val TAG = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {
        LoggerUtils.debug(TAG, "onReceive")
        getAlarmUri()?.let { playSound(context!!, it) }
    }

    /**
     * Method is used to play sound when alarm is start
     * @param context
     * @param alarmUri
     */
    private fun playSound(context: Context, alarmUri: Uri) {
        LoggerUtils.debug(TAG, "playSound [alarmUri:$alarmUri]")
        val mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(context, alarmUri)
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
        } catch (e: IOException) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
        }
    }

    /**
     * Method is used to get Uri of either
     * @return Uri of default Alarm ringtone
     */
    private fun getAlarmUri(): Uri? {
        LoggerUtils.info(TAG, "getAlarmUri")
        var alert: Uri? = RingtoneManager
            .getDefaultUri(RingtoneManager.TYPE_ALARM)
        if (alert == null) {
            alert = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            if (alert == null) {
                alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            }
        }
        return alert
    }
}