package com.giripriyadarshan.towwn

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.rememberNavController
import com.giripriyadarshan.towwn.components.NavActivity
import com.giripriyadarshan.towwn.components.NavBar
import com.giripriyadarshan.towwn.lib.WhatsappPackage
import com.giripriyadarshan.towwn.ui.theme.TOWWNTheme
import com.togitech.ccp.component.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TOWWNTheme {
                Main()
            }
        }

    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Main() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { NavBar(navController) }
        ) {
            NavActivity(navController = navController, openWhatsappContact = { number, context -> openUsingIntent(number, context) })
        }
    }

    private fun openUsingIntent(number: String, context: Context) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, ".")
        sendIntent.putExtra(
            "jid",
            PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net"
        )
        sendIntent.type = "text/plain"

        val isWhatsappInstalled = WhatsappPackage().isWhatsappInstalled(this.packageManager)
        if (isWhatsappInstalled != null) {
            sendIntent.setPackage(isWhatsappInstalled)
            startActivity(context, sendIntent, null)
        } else {
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT).show()
        }
    }

}