package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.Serializable

@Serializable
data class Level(
  val experience: Int
)

@Serializable
data class Settings(
  val temperatureFormat: String,
  val location: String
)

@Serializable
data class ProfileData(
  val name : String,
  val email: String,
  val image: String,
  val level: Level,
  val settings: Settings
)
