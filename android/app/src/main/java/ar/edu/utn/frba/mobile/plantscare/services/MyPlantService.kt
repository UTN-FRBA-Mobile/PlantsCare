package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPlantService {
    @GET("plants/{id}")
    suspend fun getPlantById(@Path("id") id: Int): PlantInfo

    @GET("plants")
    suspend fun getPlants(): List<PlantInfo>
}