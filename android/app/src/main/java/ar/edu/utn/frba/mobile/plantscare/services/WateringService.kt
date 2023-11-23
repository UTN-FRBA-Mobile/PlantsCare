package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.WateringData
import ar.edu.utn.frba.mobile.plantscare.model.WateringRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WateringService {
    @GET("watering")
    suspend fun getWatering() : List<WateringData>

    @POST("plants/{plantId}/watering")
    suspend fun postRequest(@Path("plantId") plantId: Int, @Body body: WateringRequest): Response<ResponseBody>
}