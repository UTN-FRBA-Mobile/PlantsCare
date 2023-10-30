package ar.edu.utn.frba.mobile.plantscare.network

import ar.edu.utn.frba.mobile.plantscare.services.ProfileService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_URL = "https://iivxz2d2gg.execute-api.us-east-1.amazonaws.com"
private val retrofit = Retrofit.Builder()
  .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
  .baseUrl(BASE_URL)
  .build()

object PlantsCareApi {
  val profileService : ProfileService by lazy {
    retrofit.create(ProfileService::class.java)
  }
}
