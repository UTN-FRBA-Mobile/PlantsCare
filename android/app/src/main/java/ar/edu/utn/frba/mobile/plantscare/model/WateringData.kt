package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Serializable
data class WateringData(
  @Serializable(with = LocalDateSerializer::class)
  val date: LocalDate,
  val plants: List<Plant>
)

@Serializable
data class Plant(
  val id: Int,
  val name: String,
  val type: String,
  val description: String,
  val currentWateringFrequency: Int,
  val properties: PlantProperties,
  val imageGallery: List<String>
)

@Serializer(forClass = LocalDate::class)
class LocalDateSerializer : KSerializer<LocalDate> {
  private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

  override fun serialize(encoder: Encoder, value: LocalDate) {
    encoder.encodeString(value.format(formatter))
  }

  override fun deserialize(decoder: Decoder): LocalDate {
    return LocalDate.parse(decoder.decodeString(), formatter)
  }
}