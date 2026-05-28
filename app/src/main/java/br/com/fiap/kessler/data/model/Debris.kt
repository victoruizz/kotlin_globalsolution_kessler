package br.com.fiap.kessler.data.model

data class Debris(
    val id: String,
    val name: String,
    val countryOfOrigin: String,
    val launchYear: Int,
    val altitudeKm: Double,
    val massKg: Double,
    val orbitalInclinationDegrees: Double,
    val type: String,
    val riskLevel: RiskLevel,
    val originMission: String,
    val story: String
)
