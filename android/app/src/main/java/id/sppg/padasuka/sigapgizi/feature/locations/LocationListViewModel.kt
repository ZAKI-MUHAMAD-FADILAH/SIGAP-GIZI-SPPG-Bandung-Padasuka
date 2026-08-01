package id.sppg.padasuka.sigapgizi.feature.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.sppg.padasuka.sigapgizi.domain.usecase.GetBeneficiariesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel
    @Inject
    constructor(
        private val getBeneficiariesUseCase: GetBeneficiariesUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<LocationListUiState>(LocationListUiState.Loading)
        val uiState: StateFlow<LocationListUiState> = _uiState.asStateFlow()

        init {
            loadLocations()
        }

        fun loadLocations() {
            viewModelScope.launch {
                _uiState.value = LocationListUiState.Loading
                getBeneficiariesUseCase()
                    .onSuccess { list ->
                        _uiState.value =
                            LocationListUiState.Success(
                                beneficiaries = list,
                                totalLocations = list.size,
                                totalPortions = list.sumOf { it.totalPortions },
                            )
                    }
                    .onFailure {
                        _uiState.value =
                            LocationListUiState.Error(
                                it.localizedMessage ?: "Gagal memuat daftar lokasi",
                            )
                    }
            }
        }
    }
