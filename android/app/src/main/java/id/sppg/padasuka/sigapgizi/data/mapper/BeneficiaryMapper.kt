package id.sppg.padasuka.sigapgizi.data.mapper

import id.sppg.padasuka.sigapgizi.data.remote.dto.BeneficiaryDto
import id.sppg.padasuka.sigapgizi.domain.model.Beneficiary
import id.sppg.padasuka.sigapgizi.domain.model.BeneficiaryCategory

internal fun BeneficiaryDto.toDomain(): Beneficiary =
    Beneficiary(
        id = id,
        code = code,
        name = name,
        category =
            if (category == "B3") {
                BeneficiaryCategory.B3
            } else {
                BeneficiaryCategory.PESERTA_DIDIK
            },
        teamCode = teamCode,
        smallPortions = smallPortions,
        largePortions = largePortions,
        teacherPortions = teacherPortions,
        pregnantPortions = pregnantPortions,
        breastfeedingPortions = breastfeedingPortions,
        toddlerPortions = toddlerPortions,
        totalPortions = totalPortions,
    )
