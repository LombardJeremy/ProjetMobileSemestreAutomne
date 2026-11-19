package com.grman.geofencing.items.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.grman.geofencing.ui.theme.FieldBg
import com.grman.geofencing.ui.theme.FieldBorder
import com.grman.geofencing.ui.theme.GrayLabel
import com.grman.geofencing.ui.theme.GraySubtitle
import com.grman.geofencing.ui.theme.LittleRoundedCornerShape
import com.grman.geofencing.ui.theme.TealAccent
import androidx.compose.runtime.*

// Text field avec placeholder etc + si password visibility
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = LittleRoundedCornerShape,

        // Affichage caché ou pas en fonction de visible
        visualTransformation = if (isPassword && !visible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else keyboardType),

        placeholder = { Text(placeholder, color = GrayLabel, style = MaterialTheme.typography.bodyMedium) },
        textStyle = MaterialTheme.typography.bodyMedium,

        // Icon de visibilite si password
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { visible = !visible }) {
                    Icon(
                        imageVector = if (visible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        tint = GraySubtitle
                    )
                }
            }
        } else null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg,
            focusedBorderColor = TealAccent,
            unfocusedBorderColor = FieldBorder,
            cursorColor = TealAccent
        )
    )
}

// Label gris + champ + texte d'aide optionnel
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    helper: String? = null
) {
    Column(Modifier.fillMaxWidth()) {
        Text(label,
            color = GraySubtitle,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(8.dp))

        AppTextField(
            value,
            onValueChange,
            placeholder,
            isPassword = isPassword,
            keyboardType = keyboardType
        )

        if (helper != null) {
            Spacer(Modifier.height(6.dp))
            Text(helper, color = GrayLabel, style = MaterialTheme.typography.bodySmall)
        }
    }
}