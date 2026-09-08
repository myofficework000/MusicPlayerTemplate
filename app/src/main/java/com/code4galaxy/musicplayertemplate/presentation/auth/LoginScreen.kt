package com.code4galaxy.musicplayertemplate.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.code4galaxy.musicplayertemplate.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val credentialManager = CredentialManager.create(context)

    val webClientId = stringResource(
        R.string.default_web_client_id
    )

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            onLoginSuccess()
        }
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F0F12),
            Color(0xFF21102F)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Music",
            color = Color.White,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Welcome back",
            color = Color(0xFFB8B2C2),
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(36.dp)
        )

        Text(
            text = "Sign In",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF2A2630),
                unfocusedContainerColor = Color(0xFF2A2630),
                focusedBorderColor = Color(0xFFB56CFF),
                unfocusedBorderColor = Color(0xFF3A3443),
                focusedLabelColor = Color(0xFFB56CFF),
                unfocusedLabelColor = Color(0xFFB8B2C2),
                cursorColor = Color(0xFFB56CFF)
            )
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            },
            singleLine = true,
            visualTransformation =
                if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            trailingIcon = {

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {

                    Icon(
                        imageVector =
                            if (passwordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                        contentDescription = "Toggle password visibility",
                        tint = Color(0xFFB8B2C2)
                    )
                }
            },
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF2A2630),
                unfocusedContainerColor = Color(0xFF2A2630),
                focusedBorderColor = Color(0xFFB56CFF),
                unfocusedBorderColor = Color(0xFF3A3443),
                focusedLabelColor = Color(0xFFB56CFF),
                unfocusedLabelColor = Color(0xFFB8B2C2),
                cursorColor = Color(0xFFB56CFF)
            )
        )

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        Button(
            onClick = {
                viewModel.loginUser(
                    email,
                    password
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB56CFF),
                contentColor = Color.White
            )
        ) {

            if (uiState.isLoading) {

                CircularProgressIndicator(
                    color = Color.White
                )

            } else {

                Text(
                    text = "Sign In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        uiState.error?.let { error ->

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = error,
                color = Color(0xFFFF6B6B),
                fontSize = 14.sp
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        HorizontalDivider(
            color = Color(0xFF3A3443)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Or continue with",
            color = Color(0xFFB8B2C2),
            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        OutlinedButton(
            onClick = {

                scope.launch {

                    try {

                        val googleIdOption =
                            GetGoogleIdOption.Builder()
                                .setFilterByAuthorizedAccounts(false)
                                .setServerClientId(webClientId)
                                .setAutoSelectEnabled(false)
                                .build()

                        val request =
                            GetCredentialRequest.Builder()
                                .addCredentialOption(
                                    googleIdOption
                                )
                                .build()

                        val result =
                            credentialManager.getCredential(
                                context = context,
                                request = request
                            )

                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(
                                result.credential.data
                            )

                        viewModel.loginWithGoogle(
                            googleIdTokenCredential.idToken
                        )

                    } catch (e: Exception) {

                        println(
                            "Google Login Error: ${e.message}"
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Continue with Google",
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Don't have an account? Sign-up"
            )

        }
    }
}