package com.giripriyadarshan.towwn.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giripriyadarshan.towwn.components.ChatButton
import com.giripriyadarshan.towwn.components.CodePicker

@Composable
fun MainScreen(openWhatsappContact: (String, Context) -> Unit) {
    val focusManager = LocalFocusManager.current
    val errorStatus = rememberSaveable { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                focusManager.clearFocus()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
        ) {
            Text(
                text = "Enter phone number:",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
            )
        }
        CodePicker(errorStatus)
        ChatButton( { number, context -> openWhatsappContact(number, context) }, errorStatus)
    }
}