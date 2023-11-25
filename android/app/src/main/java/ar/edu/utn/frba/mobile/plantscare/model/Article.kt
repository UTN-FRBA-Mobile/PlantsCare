package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
  val id: Int,
  val name: String,
  val coverImage: String,
  val readingTimeMinutes: Int,
  val author: String,
  val createdAt: String,
  val title: String,
  val blocks: List<Block>
)

@Serializable
data class Block(
  val type: String,
  val value: String
)