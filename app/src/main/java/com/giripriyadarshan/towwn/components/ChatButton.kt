package com.giripriyadarshan.towwn.components

import android.content.Context
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.togitech.ccp.component.getFullPhoneNumber
import com.togitech.ccp.component.isPhoneNumber

@Composable
fun ChatButton(openWhatsappContact: (String, Context) -> Unit, errorStatus: MutableState<Boolean>) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (!isPhoneNumber()) {
                openWhatsappContact(getFullPhoneNumber().drop(1), context)
            } else {
                    errorStatus.value = true
            }
        },
        modifier = Modifier
            .padding(20.dp)
            .height(50.dp)
            .width(200.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = "Chat", color = MaterialTheme.colorScheme.surface, fontSize = 20.sp)
    }
}