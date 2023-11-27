package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.Serializable


@Serializable
data class GuideData(
  val id: Int,
  val name: String,
  val coverImage: String,
  val readingTimeInMinutes: Int,
  val author: String,
  val createdAt: String,
  val title: String,
  val blocks: List<Block>,
)

@Serializable
data class Block(
  val type: String,
  val value: String
)
