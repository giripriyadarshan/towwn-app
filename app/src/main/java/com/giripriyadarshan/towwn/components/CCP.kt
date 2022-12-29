package com.giripriyadarshan.towwn.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.togitech.ccp.component.TogiCountryCodePicker

@Composable
fun CodePicker(errorStatus: MutableState<Boolean>) {
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val textStyle = TextStyle(
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
    val liveErrorStatus = rememberSaveable { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    TogiCountryCodePicker(
        text = phoneNumber.value,
        onValueChange = { phoneNumber.value = it },
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        cursorColor = MaterialTheme.colorScheme.primary,
        showCountryFlag = true,
        textStyleDefault = textStyle,
        textStyleHint = TextStyle(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0f)),
        countryCodeStyle = textStyle,
        countryCodeDialogBackgroundColor = MaterialTheme.colorScheme.surface,
        changeStatustoErrorIfError = errorStatus.value,
        turnLiveErrorStatusOn = true,
        liveErrorStatus = {
            liveErrorStatus.value = it
            if (!it) {
                errorStatus.value = false
                focusManager.clearFocus()
            }
        },

        )
}