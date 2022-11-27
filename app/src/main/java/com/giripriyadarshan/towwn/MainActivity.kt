package com.giripriyadarshan.towwn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.giripriyadarshan.towwn.R
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
                val baseUrl = "https://api.whatsapp.com/send/?phone="
                val suffixUrl = "&text&app_absent=0"
                val url = baseUrl + phoneNumber + suffixUrl
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }
}