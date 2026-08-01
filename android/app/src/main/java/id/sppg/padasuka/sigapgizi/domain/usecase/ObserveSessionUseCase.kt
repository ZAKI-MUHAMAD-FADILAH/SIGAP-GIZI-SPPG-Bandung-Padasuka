package id.sppg.padasuka.sigapgizi.domain.usecase

import id.sppg.padasuka.sigapgizi.domain.model.AuthenticatedUser
import id.sppg.padasuka.sigapgizi.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSessionUseCase
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) {
        operator fun invoke(): Flow<AuthenticatedUser?> = repository.observeCurrentUser()
    }
