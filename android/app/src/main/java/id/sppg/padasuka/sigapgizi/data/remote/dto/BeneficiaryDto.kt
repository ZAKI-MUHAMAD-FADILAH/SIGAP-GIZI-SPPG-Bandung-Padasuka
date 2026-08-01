package id.sppg.padasuka.sigapgizi.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BeneficiaryDto(
    @SerialName("id")
    val id: String,
    @SerialName("code")
    val code: String,
    @SerialName("name")
    val name: String,
    @SerialName("category")
    val category: String,
    @SerialName("team_code")
    val teamCode: String,
    @SerialName("small_portions")
    val smallPortions: Int = 0,
    @SerialName("large_portions")
    val largePortions: Int = 0,
    @SerialName("teacher_portions")
    val teacherPortions: Int = 0,
    @SerialName("pregnant_portions")
    val pregnantPortions: Int = 0,
    @SerialName("breastfeeding_portions")
    val breastfeedingPortions: Int = 0,
    @SerialName("toddler_portions")
    val toddlerPortions: Int = 0,
    @SerialName("total_portions")
    val totalPortions: Int = 0,
)
