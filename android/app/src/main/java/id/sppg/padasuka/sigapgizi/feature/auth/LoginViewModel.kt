package id.sppg.padasuka.sigapgizi.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.sppg.padasuka.sigapgizi.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val signInUseCase: SignInUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(LoginUiState())
        val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

        fun onEvent(event: LoginUiEvent) {
            when (event) {
                is LoginUiEvent.EmailOrAccountChanged -> _uiState.update { it.copy(emailOrAccountInput = event.value) }
                is LoginUiEvent.PasswordChanged -> _uiState.update { it.copy(passwordInput = event.value) }
                LoginUiEvent.ClearError -> _uiState.update { it.copy(errorMessage = null) }
                LoginUiEvent.Submit -> performLogin()
            }
        }

        private fun performLogin() {
            val email = _uiState.value.emailOrAccountInput.trim()
            val pass = _uiState.value.passwordInput

            if (email.isBlank() || pass.isBlank()) {
                _uiState.update { it.copy(errorMessage = "Mohon isi email/akun dan kata sandi") }
                return
            }

            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                signInUseCase(email, pass)
                    .onFailure {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMessage = "Kredensial tidak valid atau akun tidak terdaftar",
                            )
                        }
                    }
                    .onSuccess {
                        _uiState.update { state -> state.copy(isLoading = false) }
                    }
            }
        }
    }
