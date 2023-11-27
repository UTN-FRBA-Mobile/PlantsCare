package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.ProfileData
import retrofit2.http.GET

interface ProfileService {
  @GET("users/me")
  suspend fun getProfile(): ProfileData
}