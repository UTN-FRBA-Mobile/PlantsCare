package ar.edu.utn.frba.mobile.plantscare.model

import android.icu.text.CaseMap.Title
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

@Serializable
data class Block(
  val type: String,
  val value: String,
)