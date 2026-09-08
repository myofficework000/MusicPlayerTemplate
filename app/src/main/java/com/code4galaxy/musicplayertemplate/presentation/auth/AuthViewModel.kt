package com.code4galaxy.musicplayertemplate.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code4galaxy.musicplayertemplate.domain.model.User
import com.code4galaxy.musicplayertemplate.domain.repository.AuthRepository
import com.code4galaxy.musicplayertemplate.domain.useCase.auth.GoogleLoginUseCase
import com.code4galaxy.musicplayertemplate.domain.useCase.auth.LoginUseCase
import com.code4galaxy.musicplayertemplate.domain.useCase.auth.LogoutUseCase
import com.code4galaxy.musicplayertemplate.domain.useCase.auth.SignupUseCase
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val repository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _user = MutableStateFlow<User?>(null)
    val user : StateFlow<User?> = _user.asStateFlow()

    init {
        observeCurrentUser()
    }

    private fun observeCurrentUser() {
        viewModelScope.launch {
            repository.currentUser.collect { currentUser ->
                _user.value = currentUser

                _uiState.value = _uiState.value.copy(
                    isLoggedIn = currentUser != null
                )
            }
        }
    }

    fun loginUser(email: String, password: String){

        if(email.isBlank() || password.isBlank()){
            _uiState.value = AuthUiState(error = "Email and password are required")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = loginUseCase(email.trim(), password)

            result.onSuccess {
                _uiState.value = AuthUiState(isLoggedIn = true, isLoading = false, message = "User Logged in Successfully")
            }
                .onFailure { exception ->
                    _uiState.value = AuthUiState( isLoading = false, error = exception.message ?: ("Login Failed"))
                }
        }


    }

    fun signupUser(email: String, password: String){
        if(email.isBlank() || password.isBlank()){
            _uiState.value = AuthUiState(error = "Email and password are required")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)

            val result = signupUseCase(email.trim(), password)

            result.onSuccess {

                    _uiState.value = AuthUiState( isLoading = false, isLoggedIn = true, message = "Registration successful")
                }
                .onFailure { exception ->

                    _uiState.value = AuthUiState( isLoading = false, error = exception.message ?: "Registration failed")
                }
        }
    }

    fun loginWithGoogle(idToken: String) {

        viewModelScope.launch {

            _uiState.value = AuthUiState(
                isLoading = true
            )

            val credential = GoogleAuthProvider.getCredential(
                idToken,
                null
            )

            val result = googleLoginUseCase(credential)

            result
                .onSuccess {
                    _uiState.value = AuthUiState(
                        isLoading = false,
                        isLoggedIn = true,
                        message = "Google login successful"
                    )
                }
                .onFailure { exception ->
                    _uiState.value = AuthUiState(
                        isLoading = false,
                        error = exception.message ?: "Google login failed"
                    )
                }
        }
    }

    fun logoutUser(){
        viewModelScope.launch {
            logoutUseCase()

            _uiState.value = AuthUiState(isLoggedIn = false)
        }
    }
}