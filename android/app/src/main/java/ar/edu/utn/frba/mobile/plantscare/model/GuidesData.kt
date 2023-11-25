package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.Serializable


@Serializable
data class GuidesData(
  val id: Int,
  val name: String,
  val readingTimeInMinutes: Int,
  val author: String,
  val title: String,
  val blocks: List<Block>,
  val createdAt: String,
)

