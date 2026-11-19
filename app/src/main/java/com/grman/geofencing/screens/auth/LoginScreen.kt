package com.grman.geofencing.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grman.geofencing.R
import com.grman.geofencing.items.auth.AuthFooter
import com.grman.geofencing.items.auth.AuthLayout
import com.grman.geofencing.items.auth.ErrorMessage
import com.grman.geofencing.items.auth.LogoBadge
import com.grman.geofencing.items.common.AppButton
import com.grman.geofencing.items.forms.LabeledTextField
import com.grman.geofencing.ui.theme.TealAccent
import com.grman.geofencing.items.auth.AuthTitle
import com.grman.geofencing.ui.theme.GeoFencingTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onSubmit: (email: String, password: String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {

    // todo: viewmodel

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Layout avec btn retour + titre + footer
    AuthLayout(
        modifier = modifier,
        topTitle = stringResource(R.string.login_top),
        onBackClick = onBackClick,
        footer = {
            AuthFooter(
                text = stringResource(R.string.no_account_yet),
                actionText = stringResource(R.string.sign_up),
                onClick = onSignUpClick
            )
        }
    ) {
        Spacer(Modifier.height(24.dp))

        // Logo simple
        LogoBadge()

        Spacer(Modifier.height(24.dp))

        // Titre de login
        AuthTitle(
            title = stringResource(R.string.login_title),
            subtitle = stringResource(R.string.login_subtitle)
        )
        Spacer(Modifier.height(32.dp))

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
            placeholder = stringResource(R.string.password_placeholder),
            isPassword = true
        )

        Spacer(Modifier.height(12.dp))

        // Mot de passe oublié (à voir si on garde)
        Text(
            text = stringResource(R.string.forgot_password),
            color = TealAccent,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().clickable { onForgotPasswordClick() }
        )

        Spacer(Modifier.height(24.dp))

        // Error
        ErrorMessage(errorMessage)

        // Btn Login
        AppButton(
            text = stringResource(R.string.log_in),
            onClick = { onSubmit(email.trim(), password) },
            enabled = !isLoading,
            loading = isLoading
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun LoginScreenPreview() {
    GeoFencingTheme {
        LoginScreen()
    }
}