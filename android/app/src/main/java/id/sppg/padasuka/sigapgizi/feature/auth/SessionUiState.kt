package id.sppg.padasuka.sigapgizi.feature.auth

import id.sppg.padasuka.sigapgizi.domain.model.AuthenticatedUser

sealed interface SessionUiState {
    data object Checking : SessionUiState

    data object Unauthenticated : SessionUiState

    data class Authenticated(val user: AuthenticatedUser) : SessionUiState

    data class Inactive(val user: AuthenticatedUser) : SessionUiState

    data class Error(val message: String) : SessionUiState
}
