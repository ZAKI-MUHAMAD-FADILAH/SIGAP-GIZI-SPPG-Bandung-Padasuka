package id.sppg.padasuka.sigapgizi.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.sppg.padasuka.sigapgizi.domain.usecase.ObserveSessionUseCase
import id.sppg.padasuka.sigapgizi.domain.usecase.SignOutUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel
    @Inject
    constructor(
        observeSessionUseCase: ObserveSessionUseCase,
        private val signOutUseCase: SignOutUseCase,
    ) : ViewModel() {
        val sessionState: StateFlow<SessionUiState> =
            observeSessionUseCase()
                .map { user ->
                    when {
                        user == null -> SessionUiState.Unauthenticated
                        !user.isActive -> SessionUiState.Inactive(user)
                        else -> SessionUiState.Authenticated(user)
                    }
                }
                .catch { emit(SessionUiState.Error(it.localizedMessage ?: "ERROR_SESSION")) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = SessionUiState.Checking,
                )

        fun signOut() {
            viewModelScope.launch {
                signOutUseCase()
            }
        }
    }
