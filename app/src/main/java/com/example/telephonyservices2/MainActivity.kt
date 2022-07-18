package com.example.telephonyservices2

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import android.telephony.TelephonyManager
import android.util.Log
import com.example.telephonyservices2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var telephonyManager: TelephonyManager
    @SuppressLint("MissingPermission", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        mt("Phone Type: ${telephonyManager.phoneType}")
        mt("Data State : ${telephonyManager.dataState}")
        //mt("Data Network Type : ${telephonyManager.dataNetworkType}")
       // mt("${telephonyManager.networkOperator}")
       // mt("${telephonyManager.networkType}")
       // mt("Device Software Version :${telephonyManager.deviceSoftwareVersion}")
       // mt("IMEI : ${telephonyManager.imei}")


    telephonyManager.listen(MyPhoneStateListener(),
    PhoneStateListener.LISTEN_CALL_STATE.or(PhoneStateListener.LISTEN_USER_MOBILE_DATA_STATE).or(PhoneStateListener.LISTEN_DATA_ACTIVITY))

    }

    inner class MyPhoneStateListener : PhoneStateListener(){
        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            super.onCallStateChanged(state, phoneNumber)
            mt("State : $state $phoneNumber")
        }

        override fun onUserMobileDataStateChanged(enabled: Boolean) {
            super.onUserMobileDataStateChanged(enabled)
            mt("Mobile Data : $enabled")
        }

        override fun onDataActivity(direction: Int) {
            super.onDataActivity(direction)

            mt("Direction : $direction")
        }

        override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
            super.onSignalStrengthsChanged(signalStrength)
            mt("On Signal Strengths Changed Called : $signalStrength")
        }
        override fun onSignalStrengthChanged(asu: Int) {
            super.onSignalStrengthChanged(asu)
            mt(" Signal Strength : $asu")
        }
    }

    private fun mt(text : String){
        Log.e("tag",text)
    }
}