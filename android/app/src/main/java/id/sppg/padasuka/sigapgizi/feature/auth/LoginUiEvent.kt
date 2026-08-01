package id.sppg.padasuka.sigapgizi.feature.auth

sealed interface LoginUiEvent {
    data class EmailOrAccountChanged(val value: String) : LoginUiEvent

    data class PasswordChanged(val value: String) : LoginUiEvent

    data object Submit : LoginUiEvent

    data object ClearError : LoginUiEvent
}
