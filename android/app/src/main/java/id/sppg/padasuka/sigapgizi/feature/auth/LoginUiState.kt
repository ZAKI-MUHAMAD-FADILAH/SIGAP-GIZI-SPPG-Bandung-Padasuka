package id.sppg.padasuka.sigapgizi.feature.auth

data class LoginUiState(
    val emailOrAccountInput: String = "",
    val passwordInput: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
