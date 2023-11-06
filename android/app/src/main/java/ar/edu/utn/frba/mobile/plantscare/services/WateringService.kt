package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.WateringData
import retrofit2.http.GET

interface WateringService {
    @GET("watering/")
    suspend fun getWatering() : WateringData
}