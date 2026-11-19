package com.grman.geofencing.screens.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grman.geofencing.R
import com.grman.geofencing.items.auth.AuthFooter
import com.grman.geofencing.items.auth.AuthLayout
import com.grman.geofencing.items.auth.AuthTitle
import com.grman.geofencing.items.auth.ErrorMessage
import com.grman.geofencing.items.common.AppButton
import com.grman.geofencing.items.forms.LabeledTextField
import com.grman.geofencing.ui.theme.GeoFencingTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onSubmit: (firstName: String, email: String, password: String) -> Unit = { _, _, _ -> },
    onBackClick: () -> Unit = {},
    onLogInClick: () -> Unit = {}
) {
    // todo: viewmodel

    var firstName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Layout avec btn retour + titre + footer
    AuthLayout(
        modifier = modifier,
        topTitle = stringResource(R.string.sign_up_top),
        onBackClick = onBackClick,
        footer = {
            AuthFooter(
                text = stringResource(R.string.already_account_short),
                actionText = stringResource(R.string.log_in),
                onClick = onLogInClick
            )
        }
    ) {
        Spacer(Modifier.height(24.dp))

        // Titre
        AuthTitle(
            title = stringResource(R.string.sign_up_title),
            subtitle = stringResource(R.string.sign_up_subtitle)
        )

        Spacer(Modifier.height(32.dp))

        // Nom
        LabeledTextField(
            label = stringResource(R.string.first_name),
            value = firstName,
            onValueChange = { firstName = it },
            placeholder = stringResource(R.string.first_name_placeholder)
        )
        Spacer(Modifier.height(20.dp))

        // E-mail
        LabeledTextField(
            label = stringResource(R.string.email),
            value = email,
            onValueChange = { email = it },
            placeholder = stringResource(R.string.email_placeholder),
            keyboardType = KeyboardType.Email
        )
        Spacer(Modifier.height(20.dp))

        // Mot de passe
        LabeledTextField(
            label = stringResource(R.string.password),
            value = password,
            onValueChange = { password = it },
            placeholder = stringResource(R.string.password_min),
            isPassword = true,
            helper = stringResource(R.string.password_hint)
        )

        Spacer(Modifier.height(24.dp))

        // Error
        ErrorMessage(errorMessage)

        // Btn create account
        AppButton(
            text = stringResource(R.string.create_my_account),
            onClick = { onSubmit(firstName.trim(), email.trim(), password) },
            enabled = !isLoading,
            loading = isLoading
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun SignUpScreenPreview() {
    GeoFencingTheme {
        SignUpScreen()
    }
}