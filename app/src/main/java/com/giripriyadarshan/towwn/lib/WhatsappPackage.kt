package com.giripriyadarshan.towwn.lib

import android.content.pm.PackageManager

class WhatsappPackage {
    private val w4b = "com.whatsapp.w4b"
    private val whatsapp = "com.whatsapp"

    fun isWhatsappInstalled(pm: PackageManager): String? {
        try {
            pm.getApplicationInfo("com.whatsapp.w4b", 0)
            return w4b
        } catch (e: PackageManager.NameNotFoundException) {
//
        }
        return try {
            pm.getApplicationInfo("com.whatsapp", 0)
            whatsapp
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }
}