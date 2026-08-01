package id.sppg.padasuka.sigapgizi.feature.locations

import id.sppg.padasuka.sigapgizi.domain.model.Beneficiary

sealed interface LocationListUiState {
    data object Loading : LocationListUiState

    data class Success(
        val beneficiaries: List<Beneficiary>,
        val totalLocations: Int,
        val totalPortions: Int,
    ) : LocationListUiState

    data class Error(val message: String) : LocationListUiState
}
