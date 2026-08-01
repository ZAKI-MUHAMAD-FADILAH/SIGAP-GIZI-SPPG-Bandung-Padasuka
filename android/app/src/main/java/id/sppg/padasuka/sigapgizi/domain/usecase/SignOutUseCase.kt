package id.sppg.padasuka.sigapgizi.domain.usecase

import id.sppg.padasuka.sigapgizi.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) {
        suspend operator fun invoke(): Result<Unit> = repository.signOut()
    }
