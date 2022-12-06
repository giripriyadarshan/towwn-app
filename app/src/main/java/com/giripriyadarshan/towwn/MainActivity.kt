package com.giripriyadarshan.towwn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.hbb20.CountryCodePicker


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ccpNumberInput: CountryCodePicker = findViewById(R.id.ccp_NumberInput)
        val editTextPhoneNumber: EditText = findViewById(R.id.editText_PhoneNumber)
        ccpNumberInput.registerCarrierNumberEditText(editTextPhoneNumber)

        val filledButtonChat: Button = findViewById(R.id.filledButton_Chat)

        filledButtonChat.setOnClickListener {
            val valid = ccpNumberInput.isValidFullNumber
            if (valid) {
                val phoneNumber = ccpNumberInput.fullNumber
                openWhatsappContact(phoneNumber)

//                    val baseUrl = "https://api.whatsapp.com/send/?phone="
//                    val suffixUrl = "&text&app_absent=0"
//                    val url = baseUrl + phoneNumber + suffixUrl
//                    val i = Intent(Intent.ACTION_VIEW)
//                    i.data = Uri.parse(url)
//                    startActivity(i)

            } else {
                Snackbar.make(
                    findViewById(R.id.layout_MainPage),
                    "Please enter a valid phone number",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun openWhatsappContact(number: String) {
        val uri = Uri.parse("smsto:$number")
//        val sendIntent = Intent().apply {
//            action = Intent.ACTION_SEND_MULTIPLE
//            putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayListOf<Uri>(uri))
//            `package` = "com.whatsapp.w4b"
//            putExtra("sms_body", "Hello")
//            putExtra("chat", true)
//        }
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.type = "text/plain";
//        sendIntent.setPackage("com.whatsapp.w4b");

//        val baseUrl = "https://api.whatsapp.com/send/?phone="
//                    val suffixUrl = "&text&app_absent=0"
//                    val url = baseUrl + number + suffixUrl
//        sendIntent.data = Uri.parse(url)

        val sendIntent = Intent(Intent.ACTION_SENDTO, uri)

        when (val packageName = WhatsappPackage().isWhatsappInstalled(this.packageManager)) {
            null -> {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "WhatsApp is not installed on your device",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            else -> {
                sendIntent.setPackage(packageName)
            }
        }

        startActivity(Intent.createChooser(sendIntent, ""))
    }
}