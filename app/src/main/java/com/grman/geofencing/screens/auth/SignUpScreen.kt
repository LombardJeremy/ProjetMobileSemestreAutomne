package com.grman.geofencing.screens.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grman.geofencing.R
import com.grman.geofencing.items.auth.AuthFooter
import com.grman.geofencing.items.auth.AuthLayout
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.AuthState
import com.grman.geofencing.items.auth.AuthTitle
import com.grman.geofencing.items.auth.ErrorMessage
import com.grman.geofencing.items.common.AppButton
import com.grman.geofencing.items.forms.LabeledTextField
import com.grman.geofencing.ui.theme.GeoFencingTheme
import com.grman.geofencing.viewmodels.auth.SignUpViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onLogInClick: () -> Unit = {},
    viewModel: SignUpViewModel = viewModel()
) {
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
            value = viewModel.firstName,
            onValueChange = { viewModel.firstName = it },
            placeholder = stringResource(R.string.first_name_placeholder)
        )
        Spacer(Modifier.height(20.dp))

        // E-mail
        LabeledTextField(
            label = stringResource(R.string.email),
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = stringResource(R.string.email_placeholder),
            keyboardType = KeyboardType.Email
        )
        Spacer(Modifier.height(20.dp))

        // Mot de passe
        LabeledTextField(
            label = stringResource(R.string.password),
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = stringResource(R.string.password_min),
            isPassword = true,
            helper = stringResource(R.string.password_hint)
        )

        Spacer(Modifier.height(20.dp))

        // Confirmation de mot de passe
        LabeledTextField(
            label = stringResource(R.string.confirm_password),
            value = viewModel.confirmPassword,
            onValueChange = { viewModel.confirmPassword = it },
            placeholder = stringResource(R.string.password_min),
            isPassword = true,
            helper = stringResource(R.string.confirm_password_hint)
        )

        Spacer(Modifier.height(24.dp))

        // Error
        ErrorMessage(viewModel.errorMessage)

        // Btn create account
        AppButton(
            text = stringResource(R.string.create_my_account),
            onClick = { viewModel.signUp(onSuccess = onSignUpSuccess) },
            enabled = !viewModel.isLoading,
            loading = viewModel.isLoading
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun SignUpScreenPreview() {
    val fakeRepo = object : AuthRepository {
        override val authState = flowOf(AuthState.Unauthenticated)

        override suspend fun signIn(email: String, password: String) {}
        override suspend fun signUp(email: String, password: String, firstName: String) {}
        override suspend fun signOut() {}
    }

    GeoFencingTheme {
        SignUpScreen(viewModel = SignUpViewModel(fakeRepo))
    }
}