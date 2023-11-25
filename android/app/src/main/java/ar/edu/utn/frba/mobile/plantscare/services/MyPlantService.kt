package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface MyPlantService {
    @GET("plants/{id}")
    suspend fun getPlantById(@Path("id") id: Int): PlantInfo

    @GET("plants")
    suspend fun getPlants(): List<PlantInfo>

    @Multipart
    @POST("plants")
    suspend fun createPlant(@Part imageFile: MultipartBody.Part): PlantInfo

}