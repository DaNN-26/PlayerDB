package com.example.playerdb.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun ChangeTextField(
    value: String,
    label: String,
    desc: String,
    onValueChanged: (String) -> Unit,
    isPassword: Boolean,
    isPasswordVisible: Boolean = true,
    onPasswordVisibleClick: () -> Unit = {},
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            label = {
                Text(text = label)
            },
            placeholder = {
                Text(text = desc)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            visualTransformation = if(isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                if(isPassword) {
                    IconButton(onClick = onPasswordVisibleClick) {
                        Icon(
                            imageVector = if(isPasswordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}