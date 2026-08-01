package id.sppg.padasuka.sigapgizi.domain.usecase

import id.sppg.padasuka.sigapgizi.domain.model.AuthenticatedUser
import id.sppg.padasuka.sigapgizi.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) {
        suspend operator fun invoke(
            emailOrUsername: String,
            password: String,
        ): Result<AuthenticatedUser> = repository.signIn(emailOrUsername, password)
    }
